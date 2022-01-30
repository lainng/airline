package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.MailService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class CancelFlightCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> optionalFlightID = Optional.ofNullable(request.getParameter(RequestParameter.FLIGHT_ID));
        HttpSession session = request.getSession();

        RequestParameterValidator validator = new RequestParameterValidator();
        if (!optionalFlightID.isPresent()) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        String flightID = optionalFlightID.get();
        if(!validator.isValidID(flightID)) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        cancelFlightSetup(session, flightID);
        return new CommandResult(Pages.FLIGHTS_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private void cancelFlightSetup(HttpSession session, String flightID) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        MailService mailService = ServiceFactory.getInstance().getMailService();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        boolean isCanceled = flightService.cancelFlight(Long.parseLong(flightID));
        if (isCanceled) {
            mailService.sendCancelFlightMail(Long.parseLong(flightID), locale);
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_CANCELED_FLIGHT);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_NO_SUCH_FLIGHT);
        }
    }
}