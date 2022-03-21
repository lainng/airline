package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.MailService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AddCrewCommand implements Command {
    private static final char AMPERSAND = '&';
    private static final char EQUAL = '=';

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        Optional<String> optionalFlightID = Optional.ofNullable(request.getParameter(RequestParameter.FLIGHT_ID));
        String crewID = request.getParameter(RequestParameter.CREW_ID);
        Optional<String[]> optionalPilotsID = Optional.ofNullable(request.getParameterValues(RequestParameter.PILOTS));
        Optional<String[]> optionalAttendantsID = Optional.ofNullable(request.getParameterValues(RequestParameter.ATTENDANTS));

        CrewCreationDto crewCreationDto = new CrewCreationDto();
        if (optionalPilotsID.isPresent()
                && optionalAttendantsID.isPresent()
                && optionalFlightID.isPresent()) {
            long[] usersID = buildUsersArray(
                    toLongArrayParameters(optionalPilotsID.get()),
                    toLongArrayParameters(optionalAttendantsID.get())
            );
            crewCreationDto.setMembers(usersID);
            crewCreationDto.setAssignedFlightID(Long.parseLong(optionalFlightID.get()));
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_CREW_PARAMETERS);
            String redirectPath = Pages.CREW_ACTION_PAGE_REDIRECT;
            if (optionalFlightID.isPresent()) {
                redirectPath = buildRedirectPath(optionalFlightID.get());
            }
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }

        if (crewID.isEmpty()) {
            newCrewSetup(session, crewCreationDto);
        } else {
            crewCreationDto.setID(Long.parseLong(crewID));
            editCrewSetup(session, crewCreationDto);
        }
        return new CommandResult(Pages.CREW_ACTION_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private long[] buildUsersArray(long[]... idArrays) {
        int initialUsersNumber = 0;
        for (long[] ids : idArrays) {
            initialUsersNumber += ids.length;
        }

        long[] usersIDs = new long[initialUsersNumber];
        int copyPos = 0;
        for (long[] ids : idArrays) {
            System.arraycopy(ids, 0, usersIDs, copyPos, ids.length);
            copyPos += ids.length;
        }
        return usersIDs;
    }

    private long[] toLongArrayParameters(String[] stringParameters) {
        long[] longParameters = new long[stringParameters.length];
        for (int i = 0; i < stringParameters.length; i++) {
            longParameters[i] = Long.parseLong(stringParameters[i]);
        }
        return longParameters;
    }

    private String buildRedirectPath(String flightID) {
        return Pages.CREW_ACTION_PAGE_REDIRECT
                + AMPERSAND
                + RequestParameter.FLIGHT_ID
                + EQUAL
                + Long.parseLong(flightID);
    }

    private void newCrewSetup(HttpSession session, CrewCreationDto dto) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        MailService mailService = ServiceFactory.getInstance().getMailService();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        boolean isCreated = crewService.createCrew(dto);
        if (isCreated) {
            mailService.sendNewCrewMail(dto.getAssignedFlightID(), locale);
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_ADDED_CREW);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_FLIGHT_ALREADY_ASSIGNED);
        }
    }

    private void editCrewSetup(HttpSession session, CrewCreationDto dto) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        MailService mailService = ServiceFactory.getInstance().getMailService();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        mailService.sendEditCrewMail(dto, locale);
        crewService.editCrew(dto);
        session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_CREW);
    }
}