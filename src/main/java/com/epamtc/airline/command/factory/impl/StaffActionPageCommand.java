package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StaffActionPageCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        putInfoKeyToRequest(request.getSession(), request);

        boolean isValidID = checkRequestParameter(request.getParameterMap());
        if (!isValidID) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        String userID = request.getParameter(RequestParameter.USER_ID);
        Optional<User> optionalUser = userService.takeUser(Long.parseLong(userID));
        if (!optionalUser.isPresent()) {
            return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
        }

        request.setAttribute(RequestAttribute.EMPLOYEE, optionalUser.get());
        List<Position> positions = userService.takePositions();
        request.setAttribute(RequestAttribute.POSITIONS, positions);

        return new CommandResult(Pages.STAFF_ACTION_PAGE, RouteType.FORWARD);
    }

    private boolean checkRequestParameter(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        Optional<String[]> optionalUserIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.USER_ID));
        if (!optionalUserIDValues.isPresent()) {
            return false;
        }
        String userID = optionalUserIDValues.get()[FIRST_PARAMETER_VALUE];
        return validator.isValidID(userID);
    }
}