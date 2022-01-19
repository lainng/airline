package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
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

public class UserPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        List<Flight> flights = flightService.takeUserFlights(user);
        request.setAttribute(RequestAttribute.FLIGHTS, flights);

        backButtonSetup(request);
        return new CommandResult(Pages.USER_PAGE, RouteType.FORWARD);
    }

    private void backButtonSetup(HttpServletRequest request) {
        String currentCommand = request.getParameter(RequestParameter.COMMAND);
        request.getSession().setAttribute(SessionAttribute.CURRENT_COMMAND, currentCommand);
    }
}