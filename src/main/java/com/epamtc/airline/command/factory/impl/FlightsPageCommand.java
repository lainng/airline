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
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FlightsPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        putInfoKeyToRequest(session, request);

        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        List<Flight> flights = flightService.takeAllFlights();
        request.setAttribute(RequestAttribute.FLIGHTS,flights);

        String currentCommand = request.getParameter(RequestParameter.COMMAND);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, currentCommand);
        return new CommandResult(Pages.FLIGHTS_PAGE, RouteType.FORWARD);
    }
}