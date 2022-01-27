package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

public class FlightInfoCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);

        String flightID = request.getParameter(RequestParameter.FLIGHT_ID);
        boolean isFlightIDValid = checkRequestParameter(request.getParameterMap());
        if (!isFlightIDValid) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        Optional<Flight> optionalFlight = flightService.takeUserFlight(Long.parseLong(flightID), user);
        if (!optionalFlight.isPresent()) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }
        request.setAttribute(RequestAttribute.CURRENT_FLIGHT, optionalFlight.get());
        flightCrewSettings(request, flightID);

        return new CommandResult(Pages.FLIGHT_INFO_PAGE, RouteType.FORWARD);
    }

    private boolean checkRequestParameter(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        Optional<String[]> optionalFlightIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.FLIGHT_ID));
        if (!optionalFlightIDValues.isPresent()) {
            return false;
        }

        String[] flightIDValues = optionalFlightIDValues.get();
        if (flightIDValues.length == 0) {
            return false;
        }

        String flightID = optionalFlightIDValues.get()[FIRST_PARAMETER_VALUE];
        return validator.isValidID(flightID);
    }

    private void flightCrewSettings(HttpServletRequest request, String flightID) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        Optional<Crew> optionalCrew = crewService.takeCrewByFlightID(Long.parseLong(flightID));
        if (!optionalCrew.isPresent()) {
            request.setAttribute(RequestAttribute.NOT_ASSIGNED_CREW_KEY, InfoKey.NOT_ASSIGNED_CREW);
        } else {
            request.setAttribute(RequestAttribute.CREW, optionalCrew.get());
        }
    }
}