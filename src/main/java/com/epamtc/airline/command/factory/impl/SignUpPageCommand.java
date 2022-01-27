package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class SignUpPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<Position> positions = userService.takePositions();
        request.setAttribute(RequestAttribute.POSITIONS, positions);

        return new CommandResult(Pages.SIGN_UP_PAGE, RouteType.FORWARD);
    }
}