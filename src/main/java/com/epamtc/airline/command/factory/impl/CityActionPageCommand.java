package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

public class CityActionPageCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        putInfoKeyToRequest(session, request);

        String cityID = request.getParameter(RequestParameter.CITY_ID);
        CityService cityService = ServiceFactory.getInstance().getCityService();

        boolean isCityIDValid = checkRequestParameter(request.getParameterMap());
        if (!isCityIDValid) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        Optional<City> optionalCity = cityService.takeCity(Long.parseLong(cityID));
        if (!optionalCity.isPresent()) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }
        request.setAttribute(RequestAttribute.CITY, optionalCity.get());
        return new CommandResult(Pages.CITY_ACTION_PAGE, RouteType.FORWARD);
    }

    private boolean checkRequestParameter(Map<String, String[]> parameterMap) {
        Optional<String[]> optionalCityIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.CITY_ID));
        if (!optionalCityIDValues.isPresent()) {
            return false;
        }

        String[] cityIDValues = optionalCityIDValues.get();
        if (cityIDValues.length == 0) {
            return false;
        }

        RequestParameterValidator validator = new RequestParameterValidator();
        String cityID = optionalCityIDValues.get()[FIRST_PARAMETER_VALUE];
        return validator.isValidID(cityID);
    }
}