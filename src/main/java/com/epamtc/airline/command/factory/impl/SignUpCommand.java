package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserCreationDto dto = new UserCreationDto();

        boolean isParametersValid = checkRequestParameters(parameterMap);
        if (!isParametersValid) {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_SIGN_UP_DATA);
            return new CommandResult(Pages.SIGN_UP_PAGE, RouteType.FORWARD);
        }
        setRequestParametersToDto(parameterMap, dto);

        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean isRegistered = userService.signUp(dto);
        if(!isRegistered) {
            request.setAttribute(RequestAttribute.ERROR_KEY, InfoKey.ERROR_NOT_REGISTERED);
            setIncorrectParametersToRequest(request, dto);
            return new CommandResult(Pages.SIGN_UP_PAGE, RouteType.FORWARD);
        }
        return new CommandResult(Pages.REGISTERED_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        Optional<String[]> optionalPositionIDValues = Optional.ofNullable(parameterMap.get(RequestParameter.POSITION_ID));
        if (!optionalPositionIDValues.isPresent()) {
            return false;
        }

        String[] positionIDValues = optionalPositionIDValues.get();
        if (positionIDValues.length == 0) {
            return false;
        }

        String positionID = positionIDValues[FIRST_PARAMETER_VALUE];
        return !validator.isEmpty(parameterMap.get(RequestParameter.FIRST_NAME)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.LAST_NAME)[FIRST_PARAMETER_VALUE])
                && validator.isValidID(positionID)
                && !validator.isEmpty(parameterMap.get(RequestParameter.EMAIL)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.PASSWORD)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.CONFIRM_PASSWORD)[FIRST_PARAMETER_VALUE]);
    }

    private void setRequestParametersToDto(Map<String, String[]> parameterMap, UserCreationDto dto) {
        String email = parameterMap.get(RequestParameter.EMAIL)[FIRST_PARAMETER_VALUE];
        String password = parameterMap.get(RequestParameter.PASSWORD)[FIRST_PARAMETER_VALUE];
        String confirmPassword = parameterMap.get(RequestParameter.CONFIRM_PASSWORD)[FIRST_PARAMETER_VALUE];
        String firstName = parameterMap.get(RequestParameter.FIRST_NAME)[FIRST_PARAMETER_VALUE];
        String lastName  = parameterMap.get(RequestParameter.LAST_NAME)[FIRST_PARAMETER_VALUE];
        String positionID = parameterMap.get(RequestParameter.POSITION_ID)[FIRST_PARAMETER_VALUE];

        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setPasswordConfirmation(confirmPassword);
        dto.setPositionID(Long.parseLong(positionID));
    }

    private void setIncorrectParametersToRequest(HttpServletRequest request, UserCreationDto dto) {
        request.setAttribute(RequestAttribute.LAST_NAME, dto.getLastName());
        request.setAttribute(RequestAttribute.FIRST_NAME, dto.getFirstName());
        request.setAttribute(RequestAttribute.EMAIL, dto.getEmail());
        request.setAttribute(RequestAttribute.POSITION, dto.getPositionID());
    }
}