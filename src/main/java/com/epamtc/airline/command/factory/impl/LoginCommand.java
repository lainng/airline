package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String email = request.getParameter(RequestParameter.EMAIL);
        char[] password = request.getParameter(RequestParameter.PASSWORD).toCharArray();

        boolean isParametersValid = checkRequestParameters(request.getParameterMap());
        if (!isParametersValid) {
            setIncorrectEmailToRequest(request, email);
            return new CommandResult(Pages.LOGIN_PAGE, RouteType.FORWARD);
        }

        Optional<User> optionalUser = userService.login(email, password);
        Arrays.fill(password, ' ');

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            long roleID = user.getPosition().getRoleID();
            session.setAttribute(SessionAttribute.USER, user);
            session.setAttribute(SessionAttribute.ROLE_ID, roleID);

            if (roleID == UserRole.USER) {
                return new CommandResult(Pages.USER_PAGE_REDIRECT, RouteType.REDIRECT);
            } else if (roleID == UserRole.DISPATCHER) {
                return new CommandResult(Pages.DISPATCHER_PAGE_REDIRECT, RouteType.REDIRECT);
            } else {
                return new CommandResult(Pages.ADMIN_PAGE_REDIRECT, RouteType.REDIRECT);
            }
        } else {
            setIncorrectEmailToRequest(request, email);
            return new CommandResult(Pages.LOGIN_PAGE, RouteType.FORWARD);
        }
    }

    private void setIncorrectEmailToRequest(HttpServletRequest request, String email) {
        request.setAttribute(RequestAttribute.EMAIL, email);
        request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_LOGIN_DATA);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return !validator.isEmpty(parameterMap.get(RequestParameter.EMAIL)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.PASSWORD)[FIRST_PARAMETER_VALUE]);
    }
}