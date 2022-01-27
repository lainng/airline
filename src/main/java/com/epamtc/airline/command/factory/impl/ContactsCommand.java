package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.Pages;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContactsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(Pages.CONTACTS_PAGE, RouteType.FORWARD);
    }
}