package com.epamtc.airline.command.factory.impl;

import com.epamtc.airline.command.*;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class StaffPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> employees = userService.takeAllUsers();
        request.setAttribute(RequestAttribute.EMPLOYEES, employees);

        return new CommandResult(Pages.STAFF_PAGE, RouteType.FORWARD);
    }
}