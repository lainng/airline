package com.epamtc.airline.command;

import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface Command {

    /**
     * The basic method of command execution.
     * @param request HTTP request.
     * @param response HTTP response.
     * @return A {@link CommandResult} entity that contains the page path and request routing type.
     * @throws ServiceException if command execution is impossible.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Moves the error/success key from the session to the request. The moved key will be removed from the session.
     * @param session HTTP session.
     * @param request HTTP request.
     */
    default void putInfoKeyToRequest(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute(SessionAttribute.SUCCESS_KEY) != null) {
            request.setAttribute(RequestAttribute.SUCCESS_KEY, session.getAttribute(SessionAttribute.SUCCESS_KEY));
            session.removeAttribute(SessionAttribute.SUCCESS_KEY);
        } else if (session.getAttribute(SessionAttribute.ERROR_KEY) != null) {
            request.setAttribute(RequestAttribute.ERROR_KEY, session.getAttribute(SessionAttribute.ERROR_KEY));
            session.removeAttribute(SessionAttribute.ERROR_KEY);
        }
    }
}