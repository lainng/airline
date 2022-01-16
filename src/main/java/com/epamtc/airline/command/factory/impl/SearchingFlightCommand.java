package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.entity.Flight;
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

public class SearchingFlightCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        CityService cityService = ServiceFactory.getInstance().getCityService();
        List<City> cities = cityService.takeAllCities();
        request.setAttribute(RequestAttribute.CITIES, cities);

        String deptCityID = request.getParameter(RequestParameter.DEPARTMENT);
        String destCityID = request.getParameter(RequestParameter.DESTINATION);
        String deptDate = request.getParameter(RequestParameter.DEPARTMENT_DATE);
        String destDate = request.getParameter(RequestParameter.DESTINATION_DATE);
        SearchQuery searchQuery = new SearchQuery();

        boolean isRequestParametersValid = checkRequestParameters(request.getParameterMap());
        if (!isRequestParametersValid) {
            /*todo решить как форвардить на разные страницы (хоум и серч-резалтс)
            *  передавать на страницы объект серчкюэри чтобы установить неверные параметры поиска*/
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INVALID_SEARCH_QUERY_PARAMETERS);
            return new CommandResult(Pages.HOME_PAGE, RouteType.FORWARD);
        }

        searchQuery.setDepartmentID(Long.parseLong(deptCityID));
        searchQuery.setDestinationID(Long.parseLong(destCityID));
        try {
            searchQuery.setDeptDate(buildDate(deptDate));
            searchQuery.setDestDate(buildDate(destDate));
        } catch (ParseException e) {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INVALID_SEARCH_QUERY_PARAMETERS);
            return new CommandResult(Pages.HOME_PAGE, RouteType.FORWARD);
        }

        List<Flight> searchResult = flightService.searchFlights(searchQuery);
        request.setAttribute(RequestAttribute.FLIGHTS, searchResult);
        return new CommandResult(Pages.SEARCH_RESULTS_PAGE, RouteType.FORWARD);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return validator.isValidID(parameterMap.get(RequestParameter.DEPARTMENT)[FIRST_PARAMETER_VALUE])
                && validator.isValidID(parameterMap.get(RequestParameter.DESTINATION)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.DEPARTMENT_DATE)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.DESTINATION_DATE)[FIRST_PARAMETER_VALUE]);
    }

    private Timestamp buildDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = formatter.parse(stringDate);
        return new Timestamp(date.getTime());
    }
}