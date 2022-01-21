package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestParameter;
import com.epamtc.airline.resource.SessionAttribute;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AddFlightCommand implements Command {
    private static final int FIRST_PARAMETER_VALUE = 0;
    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private static final char AMPERSAND = '&';
    private static final char EQUAL = '=';

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String flightID = request.getParameter(RequestParameter.FLIGHT_ID);
        String routeID = request.getParameter(RequestParameter.ROUTE_ID);
        String planeID = request.getParameter(RequestParameter.PLANE_ID);
        String deptDate = request.getParameter(RequestParameter.DEPARTMENT_DATE);
        String deptTime = request.getParameter(RequestParameter.DEPARTMENT_TIME);

        FlightDto flightDto = new FlightDto();
        HttpSession session = request.getSession();

        boolean isFlightParametersValid = checkRequestParameters(request.getParameterMap());
        if (!isFlightParametersValid) {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_FLIGHT_PARAMETERS);
            String redirectPath = Pages.FLIGHT_ACTION_PAGE_REDIRECT;
            if(!flightID.isEmpty()) {
                redirectPath = buildRedirectPath(flightID);
            }
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }

        flightDto.setRouteID(Long.parseLong(routeID));
        flightDto.setPlaneID(Long.parseLong(planeID));
        try {
            flightDto.setDepartureTime(buildDepartureDateTime(deptDate, deptTime));
        } catch (ParseException e) {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_FLIGHT_PARAMETERS);
            String redirectPath = Pages.FLIGHT_ACTION_PAGE_REDIRECT;
            if(!flightID.isEmpty()) {
                redirectPath = buildRedirectPath(flightID);
            }
            return new CommandResult(redirectPath, RouteType.REDIRECT);
        }

        if (flightID.isEmpty()) {
            newFlightSetup(session, flightDto);
        } else {
            flightDto.setID(Long.parseLong(flightID));
            editFlightSetup(session, flightDto);
        }
        return new CommandResult(Pages.FLIGHT_ACTION_PAGE_REDIRECT, RouteType.REDIRECT);
    }

    private Timestamp buildDepartureDateTime(String deptDate, String deptTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = formatter.parse(deptDate + " " + deptTime);
        return new Timestamp(date.getTime());
    }

    private boolean checkRequestParameters(Map<String, String[]> parameterMap) {
        RequestParameterValidator validator = new RequestParameterValidator();
        return validator.isValidID(parameterMap.get(RequestParameter.ROUTE_ID)[FIRST_PARAMETER_VALUE])
                && validator.isValidID(parameterMap.get(RequestParameter.PLANE_ID)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.DEPARTMENT_DATE)[FIRST_PARAMETER_VALUE])
                && !validator.isEmpty(parameterMap.get(RequestParameter.DEPARTMENT_TIME)[FIRST_PARAMETER_VALUE]);
    }

    private void newFlightSetup(HttpSession session, FlightDto flightDto) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        FlightStatus flightStatus = new FlightStatus(FlightCondition.SCHEDULED);
        flightDto.setFlightStatus(flightStatus);
        boolean isFlightCreated = flightService.createFlight(flightDto);
        if (isFlightCreated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_ADDED_FLIGHT);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_FLIGHT_PARAMETERS);
        }
    }

    private void editFlightSetup(HttpSession session, FlightDto flightDto) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        boolean isFlightUpdated = flightService.editFlight(flightDto);
        if (isFlightUpdated) {
            session.setAttribute(SessionAttribute.SUCCESS_KEY, InfoKey.SUCCESS_UPDATED_FLIGHT);
        } else {
            session.setAttribute(SessionAttribute.ERROR_KEY, InfoKey.ERROR_INCORRECT_FLIGHT_PARAMETERS);
        }
    }

    private String buildRedirectPath(String flightID) {
        return Pages.FLIGHT_ACTION_PAGE_REDIRECT
                + AMPERSAND
                + RequestParameter.FLIGHT_ID
                + EQUAL
                + Long.parseLong(flightID);
    }

}