package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.MailService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class DeleteCrewCommand implements Command {
    @Override

    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Optional<String> optionalCrewID = Optional.ofNullable(request.getParameter(RequestParameter.CREW_ID));

        if (!optionalCrewID.isPresent()) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        RequestParameterValidator validator = new RequestParameterValidator();
        String crewID = optionalCrewID.get();
        if (!validator.isValidID(crewID)) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }
        deleteCrewSetup(session, crewID);
        return new CommandResult(Pages.DISPATCHER_CREWS_REDIRECT, RouteType.REDIRECT);
    }

    private void deleteCrewSetup(HttpSession session, String crewID) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        MailService mailService = ServiceFactory.getInstance().getMailService();

        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        Optional<Crew> optionalCrew = crewService.takeCrewByID(Long.parseLong(crewID));

        boolean isCrewDeleted = crewService.deleteCrew(Long.parseLong(crewID));
        if (isCrewDeleted && optionalCrew.isPresent()) {
            mailService.sendDeleteCrewMail(optionalCrew.get(), locale);
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_DELETED_CREW);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_NO_SUCH_CREW_OR_FLIGHT_CANCELED);
        }
    }
}