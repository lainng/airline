package com.epamtc.airline.command;

import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

    /**
     * The basic method of command execution.
     * @param request HTTP request.
     * @param response HTTP response.
     * @return A {@link CommandResult} entity that contains the page path and request routing type.
     * @throws ServiceException if command execution is impossible.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}