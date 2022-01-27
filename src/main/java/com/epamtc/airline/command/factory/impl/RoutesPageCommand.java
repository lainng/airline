package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class RoutesPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        List<Route> routes = routeService.takeAllRoutes();
        request.setAttribute(RequestAttribute.ROUTES, routes);
        return new CommandResult(Pages.ROUTES_PAGE, RouteType.FORWARD);
    }
}