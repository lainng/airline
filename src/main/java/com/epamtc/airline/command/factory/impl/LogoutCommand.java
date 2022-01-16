package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.RouteType;
import com.epamtc.airline.command.UserRole;
import com.epamtc.airline.resource.Pages;
import com.epamtc.airline.resource.SessionAttribute;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String currentLocale = (String) session.getAttribute(SessionAttribute.LOCALE);
        session.invalidate();

        session = request.getSession();
        session.setAttribute(SessionAttribute.ROLE_ID, UserRole.GUEST);
        session.setAttribute(SessionAttribute.LOCALE, currentLocale);
        return new CommandResult(Pages.HOME_PAGE_REDIRECT, RouteType.REDIRECT);
    }
}