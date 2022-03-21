package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class SignUpPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        UserService userService = ServiceFactory.getInstance().getUserService();
        List<Position> positions = userService.takePositions();
        UserCreationDto dto = (UserCreationDto) session.getAttribute(SessionAttribute.SIGN_UP_DTO);
        request.setAttribute(RequestAttribute.POSITIONS, positions);
        request.setAttribute(RequestAttribute.SIGN_UP_DTO, dto);

        return new CommandResult(Pages.SIGN_UP_PAGE, RouteType.FORWARD);
    }
}