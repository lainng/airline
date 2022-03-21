package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

public class ConfirmFlightCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        User currentUser = (User) session.getAttribute(SessionAttribute.USER);

        boolean isValidID = checkRequestParameter(request.getParameterMap());
        if (!isValidID) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        String flightID = request.getParameter(RequestParameter.FLIGHT_ID);
        boolean isConfirmed = flightService.confirmFlight(Long.parseLong(flightID), currentUser);
        if (!isConfirmed) {
            if (currentUser.getPosition().getRoleID() == UserRole.USER) {
                session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_NO_SUCH_CREW_OR_FLIGHT_CANCELED);
            } else {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }
        }
        return new CommandResult(Pages.USER_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameter(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        Optional<String[]> optionalFlightIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.FLIGHT_ID));
        if (!optionalFlightIDValues.isPresent()) {
            return false;
        }
        String flightID = optionalFlightIDValues.get()[FIRST_PARAMETER_VALUE];
        return validator.isValidID(flightID);
    }
}