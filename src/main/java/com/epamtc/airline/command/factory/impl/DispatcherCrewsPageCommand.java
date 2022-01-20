package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class DispatcherCrewsPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        List<Crew> crews = crewService.takeAllCrews();
        request.setAttribute(RequestAttribute.CREWS, crews);

        return new CommandResult(Pages.DISPATCHER_CREWS, RouteType.FORWARD);
    }
}