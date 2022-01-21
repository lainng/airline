package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.resource.RequestParameter;
import com.epamtc.airline.resource.SessionAttribute;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

public class AddCityCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        City city = new City();

        boolean isRequestParametersValid = checkRequestParameter(request.getParameterMap());
        if (isRequestParametersValid) {
            String cityName = request.getParameter(RequestParameter.CITY);
            city.setName(cityName);
        } else {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_CITY_PARAMETERS);
            return new CommandResult(Pages.CITIES_PAGE_REDIRECT, RouteType.FORWARD);
        }

        Optional<String> optionalCityID = Optional.ofNullable(request.getParameter(RequestParameter.CITY_ID));
        if (optionalCityID.isPresent()) {
            String cityID = optionalCityID.get();
            city.setID(Long.parseLong(cityID));
            editCitySetup(session, city);
        } else {
            newCitySetup(session, city);
        }
        return new CommandResult(Pages.CITIES_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameter(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return !validator.isEmpty(parameterMap.get(RequestParameter.CITY)[FIRST_PARAMETER_VALUE]);
    }

    private void newCitySetup(HttpSession session, City city) throws ServiceException {
        CityService cityService = ServiceFactory.getInstance().getCityService();
        boolean isCityCreated = cityService.createCity(city);
        if (isCityCreated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_ADDED_CITY);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_CITY_PARAMETERS);
        }
    }

    private void editCitySetup(HttpSession session, City city) throws ServiceException {
        CityService cityService = ServiceFactory.getInstance().getCityService();
        boolean isCityUpdated = cityService.editCity(city);
        if (isCityUpdated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_CITY);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_CITY_PARAMETERS);
        }
    }
}