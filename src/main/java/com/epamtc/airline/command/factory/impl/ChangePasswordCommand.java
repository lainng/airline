package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class ChangePasswordCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        UserCreationDto dto = new UserCreationDto();

        boolean isParametersValid = checkRequestParameters(parameterMap);
        if (isParametersValid) {
            dto.setID(user.getID());
            setParametersFromRequestToDto(parameterMap, dto);
        } else {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_PASSWORD);
            return new CommandResult(Pages.SETTINGS_PAGE, RouteType.FORWARD);
        }

        changingPasswordSetup(session, dto);
        return new CommandResult(Pages.SETTINGS_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return !validator.isEmpty(parameterMap.get(RequestParameter.PASSWORD)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.CONFIRM_PASSWORD)[FIRST_PARAMETER_VALUE]);
    }

    private void setParametersFromRequestToDto(Map<String, String[]> parameterMap, UserCreationDto dto) {
        String password = parameterMap.get(RequestParameter.PASSWORD)[FIRST_PARAMETER_VALUE];
        String confirmPassword = parameterMap.get(RequestParameter.CONFIRM_PASSWORD)[FIRST_PARAMETER_VALUE];

        dto.setPassword(password);
        dto.setPasswordConfirmation(confirmPassword);
    }

    private void changingPasswordSetup(HttpSession session, UserCreationDto dto) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean isPasswordChanged = userService.editUserPassword(dto);
        if (isPasswordChanged) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.PASSWORD_CHANGED);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_PASSWORD);
        }
    }
}