package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

public class AddRouteCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final char AMPERSAND = '&';
    private static final char EQUAL = '=';

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        String routeID = request.getParameter(RequestParameter.ROUTE_ID);
        RouteDto routeDto = new RouteDto();

        boolean isRequestParametersValid = checkRequestParameters(parameterMap);
        if (!isRequestParametersValid) {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_ROUTE_PARAMETERS);
            String redirectPath = Pages.ROUTE_ACTION_PAGE_REDIRECT;
            if (!routeID.isEmpty()) {
                redirectPath = buildRedirectPath(routeID);
            }
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }

        setParametersToEntity(parameterMap, routeDto);
        if (routeID.isEmpty()) {
            newRouteSetup(session, routeDto);
        } else {
            routeDto.setID(Long.parseLong(routeID));
            editRouteSetup(session, routeDto);
        }
        return new CommandResult(Pages.ROUTE_ACTION_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator  =new RequestParameterValidator();
        Optional<String[]> optionalDeptID = Optional.ofNullable(parameterMap.get(RequestParameter.DEPARTMENT));
        Optional<String[]> optionalDestID = Optional.ofNullable(parameterMap.get(RequestParameter.DESTINATION));

        if (!optionalDeptID.isPresent() || !optionalDestID.isPresent()) {
            return false;
        }

        String[] deptID = optionalDeptID.get();
        String[] destID = optionalDestID.get();

        return validator.isValidID(deptID[FIRST_PARAMETER_VALUE])
                && validator.isValidID(destID[FIRST_PARAMETER_VALUE])
                && validator.isNumeric(parameterMap.get(RequestParameter.DISTANCE)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.DURATION)[FIRST_PARAMETER_VALUE]);
    }

    private Time buildDurationTime(String timeString) {
        LocalTime localTime = LocalTime.parse(timeString);
        return Time.valueOf(localTime);
    }

    private void newRouteSetup(HttpSession session, RouteDto routeDto) throws ServiceException {
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        boolean isRouteCreated = routeService.createRoute(routeDto);
        if (isRouteCreated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_ADDED_ROUTE);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_ROUTE_PARAMETERS);
        }
    }

    private void editRouteSetup(HttpSession session, RouteDto routeDto) throws ServiceException {
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        boolean isRouteEdited = routeService.editRoute(routeDto);
        if (isRouteEdited) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_ROUTE);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_ROUTE_PARAMETERS);
        }
    }

    private String buildRedirectPath(String routeID) {
        return Pages.ROUTE_ACTION_PAGE_REDIRECT
                + AMPERSAND
                + RequestParameter.ROUTE_ID
                + EQUAL
                + Long.parseLong(routeID);
    }

    private void setParametersToEntity(Map<String, String[]> parameterMap, RouteDto routeDto) {
        String deptID = parameterMap.get(RequestParameter.DEPARTMENT)[FIRST_PARAMETER_VALUE];
        String destID = parameterMap.get(RequestParameter.DESTINATION)[FIRST_PARAMETER_VALUE];
        String distance = parameterMap.get(RequestParameter.DISTANCE)[FIRST_PARAMETER_VALUE];
        String duration = parameterMap.get(RequestParameter.DURATION)[FIRST_PARAMETER_VALUE];

        routeDto.setDepartureID(Long.parseLong(deptID));
        routeDto.setDestinationID(Long.parseLong(destID));
        routeDto.setDistance(Integer.parseInt(distance));
        routeDto.setDuration(buildDurationTime(duration));

    }
}