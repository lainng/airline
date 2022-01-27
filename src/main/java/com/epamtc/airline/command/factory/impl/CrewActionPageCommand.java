package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class CrewActionPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        putInfoKeyToRequest(session, request);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        CrewService crewService = factory.getCrewService();
        FlightService flightService = factory.getFlightService();

        List<User> users = userService.takeAllUsers();
        List<Flight> unassignedFlights = flightService.takeUnassignedFlights();

        Optional<String> optionalFlightID = Optional.ofNullable(request.getParameter(RequestParameter.FLIGHT_ID));
        Optional<Crew> optionalCrew;
        Optional<Flight> assignedFlight;

        if(optionalFlightID.isPresent()) {
            String flightID = optionalFlightID.get();
            RequestParameterValidator validator = new RequestParameterValidator();
            if (!validator.isValidID(flightID)) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            optionalCrew = crewService.takeCrewByFlightID(Long.parseLong(flightID));
            assignedFlight = flightService.takeFlight(Long.parseLong(flightID));

            if (optionalCrew.isPresent()) {
                editCrewSettings(request, optionalCrew.get());
            } else if (assignedFlight.isPresent()) {
                assignCrewSettings(request, assignedFlight.get());
            } else {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }
        } else {
            request.setAttribute(RequestAttribute.CREW_PAGE_KEY, InfoKey.NEW_CREW);
        }
        request.setAttribute(RequestAttribute.FLIGHTS, unassignedFlights);
        request.setAttribute(RequestAttribute.EMPLOYEES, users);

        return new CommandResult(Pages.CREW_ACTION_PAGE, RouteType.FORWARD);
    }

    private void editCrewSettings(HttpServletRequest request, Crew crew) {
        request.setAttribute(RequestAttribute.CREW, crew);
        request.setAttribute(RequestAttribute.CREW_PAGE_KEY, InfoKey.EDIT_CREW);
    }

    private void assignCrewSettings(HttpServletRequest request, Flight assignedFlight) {
        request.setAttribute(RequestAttribute.CURRENT_FLIGHT, assignedFlight);
        request.setAttribute(RequestAttribute.CREW_PAGE_KEY, InfoKey.NEW_CREW);
    }
}