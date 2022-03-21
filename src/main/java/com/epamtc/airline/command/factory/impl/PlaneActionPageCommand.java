package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class PlaneActionPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> optionalPlaneID = Optional.ofNullable(request.getParameter(RequestParameter.PLANE_ID));
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        if (optionalPlaneID.isPresent()) {
            String planeID = optionalPlaneID.get();
            RequestParameterValidator validator = new RequestParameterValidator();
            if (!validator.isValidID(planeID)) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }

            Optional<Plane> optionalPlane = planeService.takePlane(Long.parseLong(optionalPlaneID.get()));
            if (!optionalPlane.isPresent()) {
                return new CommandResult(Pages.ERROR_404, RouteType.FORWARD);
            }
            editPlaneSetup(request, optionalPlane.get());
        } else {
            request.setAttribute(RequestAttribute.PLANE_ACTION_PAGE_KEY, InfoKey.NEW_PLANE);
        }
        return new CommandResult(Pages.PLANE_ACTION_PAGE, RouteType.FORWARD);
    }

    private void editPlaneSetup(HttpServletRequest request, Plane plane) {
        request.setAttribute(RequestAttribute.PLANE, plane);
        request.setAttribute(RequestAttribute.PLANE_ACTION_PAGE_KEY, InfoKey.EDIT_PLANE);
    }
}