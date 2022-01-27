package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public class CrewsPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> optionalUserID = Optional.ofNullable(request.getParameter(RequestParameter.USER_ID));
        UserService userService = ServiceFactory.getInstance().getUserService();
        CrewService crewService = ServiceFactory.getInstance().getCrewService();

        List<Crew> crews;
        if (optionalUserID.isPresent()) {
            String userID = optionalUserID.get();
            RequestParameterValidator validator = new RequestParameterValidator();
            if (!validator.isValidID(userID)) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            Optional<User> optionalUser = userService.takeUser(Long.parseLong(userID));
            if (!optionalUser.isPresent()) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            crews = crewService.takeUserCrews(Long.parseLong(userID));
            request.setAttribute(RequestAttribute.CREWS, crews);
            request.setAttribute(RequestAttribute.EMPLOYEE, optionalUser.get());
        } else {
            crews = crewService.takeAllCrews();
            request.setAttribute(RequestAttribute.CREWS, crews);
        }
        return new CommandResult(Pages.CREWS_PAGE, RouteType.FORWARD);
    }
}