package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class PlanesPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        List<Plane> planes = planeService.takeAllPlanes();
        request.setAttribute(RequestAttribute.PLANES, planes);
        return new CommandResult(Pages.PLANES_PAGE, RouteType.FORWARD);
    }
}