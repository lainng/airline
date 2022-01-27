package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Flight;
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

        return new CommandResult(Pages.FLIGHTS_PAGE, RouteType.FORWARD);
    }
}