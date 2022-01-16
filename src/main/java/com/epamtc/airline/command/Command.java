package com.epamtc.airline.command;

import com.epamtc.airline.resource.RequestAttribute;
import com.epamtc.airline.resource.SessionAttribute;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
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