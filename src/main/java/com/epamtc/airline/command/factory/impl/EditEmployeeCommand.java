package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

public class EditEmployeeCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final char AMPERSAND = '&';
    private static final char EQUAL = '=';

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        UserCreationDto dto = new UserCreationDto();
        String userID = request.getParameter(RequestParameter.USER_ID);
        dto.setID(Long.parseLong(userID));

        boolean isParametersValid = checkRequestParameters(parameterMap);
        if (!isParametersValid) {
            String redirectPath = buildRedirectPath(dto);
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_EMPLOYEE_DATA);
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }
        setParametersToDto(parameterMap, dto);
        editEmployeeSetup(session, dto);
        return new CommandResult(Pages.STAFF_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        Optional<String[]> optionalPositionID = Optional.ofNullable(parameterMap.get(RequestParameter.POSITION_ID));
        if (!optionalPositionID.isPresent()) {
            return false;
        }
        String positionID = optionalPositionID.get()[FIRST_PARAMETER_VALUE];

        return validator.isValidID(positionID)
                && !validator.isEmpty(parameterMap.get(RequestParameter.FIRST_NAME)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.LAST_NAME)[FIRST_PARAMETER_VALUE]);
    }

    private void setParametersToDto(Map<String, String[]> parameterMap, UserCreationDto dto) {
        String firstName = parameterMap.get(RequestParameter.FIRST_NAME)[FIRST_PARAMETER_VALUE];
        String lastName = parameterMap.get(RequestParameter.LAST_NAME)[FIRST_PARAMETER_VALUE];
        long positionID = Long.parseLong(parameterMap.get(RequestParameter.POSITION_ID)[FIRST_PARAMETER_VALUE]);

        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setPositionID(positionID);
    }

    private void editEmployeeSetup(HttpSession session, UserCreationDto dto) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean isEdited = userService.editUser(dto);
        if (isEdited) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_EMPLOYEE);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_EMPLOYEE_DATA);
        }
    }

    private String buildRedirectPath(UserCreationDto dto) {
        return Pages.STAFF_ACTION_PAGE_REDIRECT
                + AMPERSAND
                + RequestParameter.USER_ID
                + EQUAL
                + dto.getID();
    }
}