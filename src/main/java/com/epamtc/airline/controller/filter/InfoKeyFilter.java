package com.epamtc.airline.controller.filter;

import com.epamtc.airline.command.RequestAttribute;
import com.epamtc.airline.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class InfoKeyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.SUCCESS_KEY) != null) {
            request.setAttribute(RequestAttribute.SUCCESS_KEY, session.getAttribute(SessionAttribute.SUCCESS_KEY));
            session.removeAttribute(SessionAttribute.SUCCESS_KEY);
        } else if (session.getAttribute(SessionAttribute.ERROR_KEY) != null) {
            request.setAttribute(RequestAttribute.ERROR_KEY, session.getAttribute(SessionAttribute.ERROR_KEY));
            session.removeAttribute(SessionAttribute.ERROR_KEY);
        }
        filterChain.doFilter(request, servletResponse);
    }
}
