package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class HomePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CityService cityService = ServiceFactory.getInstance().getCityService();
        List<City> cities = cityService.takeAllCities();
        request.setAttribute(RequestAttribute.CITIES, cities);
        return new CommandResult(Pages.HOME_PAGE, RouteType.FORWARD);
    }
}