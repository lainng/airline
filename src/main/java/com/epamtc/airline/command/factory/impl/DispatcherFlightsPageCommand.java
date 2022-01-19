package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.resource.RequestParameter;
import com.epamtc.airline.resource.SessionAttribute;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class DispatcherFlightsPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        List<Flight> flights = flightService.takeAllFlights();
        request.setAttribute(RequestAttribute.FLIGHTS, flights);

        backButtonSetup(request);
        return new CommandResult(Pages.DISPATCHER_FLIGHTS, RouteType.FORWARD);
    }

    private void backButtonSetup(HttpServletRequest request) {
        String currentCommand = request.getParameter(RequestParameter.COMMAND);
        request.getSession().setAttribute(SessionAttribute.CURRENT_COMMAND, currentCommand);
    }
}