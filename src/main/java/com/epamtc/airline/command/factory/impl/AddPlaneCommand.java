package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class AddPlaneCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final char AMPERSAND = '&';
    private static final char EQUAL = '=';

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        String planeID = request.getParameter(RequestParameter.PLANE_ID);
        Plane plane = new Plane();

        boolean isParametersValid = checkRequestParameters(parameterMap);
        if (!isParametersValid) {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_PLANE_PARAMETERS);
            String redirectPath = Pages.PLANE_ACTION_PAGE_REDIRECT;
            if (!planeID.isEmpty()) {
                redirectPath = buildRedirectPath(planeID);
            }
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }

        setParametersToEntity(parameterMap, plane);
        if (planeID.isEmpty()) {
            newPlaneSetup(session, plane);
        } else {
            plane.setID(Long.parseLong(planeID));
            editPlaneSetup(session, plane);
        }
        return new CommandResult(Pages.PLANE_ACTION_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return !validator.isEmpty(parameterMap.get(RequestParameter.MODEL)[FIRST_PARAMETER_VALUE])
                && validator.isNumeric(parameterMap.get(RequestParameter.FLIGHT_RANGE)[FIRST_PARAMETER_VALUE])
                && validator.isNumeric(parameterMap.get(RequestParameter.PASSENGER_CAPACITY)[FIRST_PARAMETER_VALUE])
                && validator.isNumeric(parameterMap.get(RequestParameter.FLYING_HOURS)[FIRST_PARAMETER_VALUE]);
    }

    private void newPlaneSetup(HttpSession session, Plane plane) throws ServiceException {
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        boolean isPlaneCreated = planeService.createPlane(plane);
        if (isPlaneCreated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_ADDED_PLANE);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_PLANE_PARAMETERS);
        }
    }

    private void editPlaneSetup(HttpSession session, Plane plane) throws ServiceException {
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        boolean isPlaneUpdated = planeService.editPlane(plane);
        if(isPlaneUpdated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_PLANE);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_PLANE_PARAMETERS);
        }
    }

    private String buildRedirectPath(String planeID) {
        return Pages.PLANE_ACTION_PAGE_REDIRECT
                + AMPERSAND
                + RequestParameter.PLANE_ID
                + EQUAL
                + Long.parseLong(planeID);
    }

    private void setParametersToEntity(Map<String, String[]> parameterMap, Plane plane) {
        String model = parameterMap.get(RequestParameter.MODEL)[FIRST_PARAMETER_VALUE];
        String flightRange = parameterMap.get(RequestParameter.FLIGHT_RANGE)[FIRST_PARAMETER_VALUE];
        String passCapacity = parameterMap.get(RequestParameter.PASSENGER_CAPACITY)[FIRST_PARAMETER_VALUE];
        String flyingHours = parameterMap.get(RequestParameter.FLYING_HOURS)[FIRST_PARAMETER_VALUE];

        plane.setModel(model);
        plane.setFlyingHours(Integer.parseInt(flyingHours));
        plane.setFlightRange(Integer.parseInt(flightRange));
        plane.setPassengerCapacity(Integer.parseInt(passCapacity));
    }

}