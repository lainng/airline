package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public class FlightActionPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RouteService routeService = serviceFactory.getRouteService();
        PlaneService planeService = serviceFactory.getPlaneService();
        FlightService flightService = serviceFactory.getFlightService();

        List<Plane> planes = planeService.takeAllPlanes();
        List<Route> routes = routeService.takeAllRoutes();
        request.setAttribute(RequestAttribute.PLANES, planes);
        request.setAttribute(RequestAttribute.ROUTES, routes);

        Optional<String> optionalFlightID = Optional.ofNullable(request.getParameter(RequestParameter.FLIGHT_ID));
        if (optionalFlightID.isPresent()) {
            String flightID = optionalFlightID.get();
            RequestParameterValidator validator = new RequestParameterValidator();
            if (!validator.isValidID(flightID)) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            Optional<Flight> optionalFlight = flightService.takeFlight(Long.parseLong(flightID));
            if (!optionalFlight.isPresent()) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }
            editFlightSetup(request, optionalFlight.get());
        } else {
            request.setAttribute(RequestAttribute.FLIGHT_ACTION_PAGE_KEY, InfoKey.NEW_FLIGHT);
        }
        return new CommandResult(Pages.FLIGHT_ACTION_PAGE, RouteType.FORWARD);
    }

    private void editFlightSetup(HttpServletRequest request, Flight flight) {
        request.setAttribute(RequestAttribute.CURRENT_FLIGHT, flight);
        request.setAttribute(RequestAttribute.FLIGHT_ACTION_PAGE_KEY, InfoKey.EDIT_FLIGHT);
    }
}