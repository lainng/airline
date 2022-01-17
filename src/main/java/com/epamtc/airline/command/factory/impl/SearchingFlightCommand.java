package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.entity.dto.SearchQuery;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.resource.RequestParameter;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SearchingFlightCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        CityService cityService = ServiceFactory.getInstance().getCityService();

        FlightStatus scheduledStatus = flightService.takeFlightStatus(FlightCondition.SCHEDULED);
        List<City> cities = cityService.takeAllCities();

        request.setAttribute(RequestAttribute.CITIES, cities);
        request.setAttribute(RequestAttribute.FLIGHT_STATUS, scheduledStatus);

        String deptCityID = request.getParameter(RequestParameter.DEPARTMENT);
        String destCityID = request.getParameter(RequestParameter.DESTINATION);
        String deptDate = request.getParameter(RequestParameter.DEPARTMENT_DATE);
        String destDate = request.getParameter(RequestParameter.DESTINATION_DATE);
        SearchQuery searchQuery = new SearchQuery();

        boolean isRequestParametersValid = checkRequestParameters(request.getParameterMap());
        if (!isRequestParametersValid) {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INVALID_SEARCH_QUERY_PARAMETERS);
            return new CommandResult(Pages.SEARCH_RESULTS_PAGE, RouteType.FORWARD);
        }

        searchQuery.setDepartmentID(Long.parseLong(deptCityID));
        searchQuery.setDestinationID(Long.parseLong(destCityID));
        try {
            searchQuery.setDeptDate(buildDate(deptDate));
            searchQuery.setDestDate(buildDate(destDate));
        } catch (ParseException e) {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INVALID_SEARCH_QUERY_PARAMETERS);
            return new CommandResult(Pages.SEARCH_RESULTS_PAGE, RouteType.FORWARD);
        }

        List<Flight> searchResult = flightService.searchFlights(searchQuery);
        request.setAttribute(RequestAttribute.FLIGHTS, searchResult);
        return new CommandResult(Pages.SEARCH_RESULTS_PAGE, RouteType.FORWARD);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();

        Optional<String[]> optionalDeptIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.DEPARTMENT));
        Optional<String[]> optionalDestIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.DESTINATION));
        Optional<String[]> optionalDeptDateValues = Optional.ofNullable(parameterMap.get(RequestParameter.DEPARTMENT_DATE));
        Optional<String[]> optionalDestDateValues = Optional.ofNullable(parameterMap.get(RequestParameter.DESTINATION_DATE));
        if (!optionalDeptIDValues.isPresent() || !optionalDestIDValues.isPresent()
                || !optionalDeptDateValues.isPresent() || !optionalDestDateValues.isPresent()) {
            return false;
        }

        String[] deptIDValues = optionalDeptIDValues.get();
        String[] destIDValues = optionalDestIDValues.get();
        String[] deptDateValues = optionalDeptDateValues.get();
        String[] destDateValues = optionalDestDateValues.get();
        if (deptIDValues.length == 0 || destIDValues.length == 0
            || deptDateValues.length == 0 || destDateValues.length == 0) {
            return false;
        }

        return validator.isValidID(deptIDValues[FIRST_PARAMETER_VALUE])
                && validator.isValidID(destIDValues[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(deptDateValues[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(destDateValues[FIRST_PARAMETER_VALUE]);
    }

    private Timestamp buildDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = formatter.parse(stringDate);
        return new Timestamp(date.getTime());
    }
}