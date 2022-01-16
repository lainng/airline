package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AboutCompanyCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(Pages.ABOUT_COMPANY_PAGE, RouteType.FORWARD);
    }
}