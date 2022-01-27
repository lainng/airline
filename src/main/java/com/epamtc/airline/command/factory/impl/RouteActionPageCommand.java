package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class RouteActionPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        putInfoKeyToRequest(session, request);

        CityService cityService = ServiceFactory.getInstance().getCityService();
        RouteService routeService = ServiceFactory.getInstance().getRouteService();

        Optional<String> optionalRouteID = Optional.ofNullable(request.getParameter(RequestParameter.ROUTE_ID));
        List<City> cities = cityService.takeAllCities();

        if (optionalRouteID.isPresent()) {
            RequestParameterValidator validator = new RequestParameterValidator();
            String routeID = optionalRouteID.get();
            if (!validator.isValidID(routeID)) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            Optional<Route> optionalRoute = routeService.takeRoute(Long.parseLong(routeID));
            if (!optionalRoute.isPresent()) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            request.setAttribute(RequestAttribute.ROUTE, optionalRoute.get());
            request.setAttribute(RequestAttribute.ROUTE_ACTION_PAGE_KEY, InfoKey.EDIT_ROUTE);
        } else {
            request.setAttribute(RequestAttribute.ROUTE_ACTION_PAGE_KEY, InfoKey.NEW_ROUTE);
        }
        request.setAttribute(RequestAttribute.CITIES, cities);
        return new CommandResult(Pages.ROUTE_ACTION_PAGE, RouteType.FORWARD);
    }
}