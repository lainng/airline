package com.epamtc.airline.controller.filter;

import com.epamtc.airline.command.RequestParameter;
import com.epamtc.airline.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LocaleFilter implements Filter {
    private static final String DEFAULT_LOCALE_PARAM = "defaultLocale";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (request.getSession().getAttribute(SessionAttribute.LOCALE) == null || request.getSession().getAttribute(SessionAttribute.LOCALE).equals("")) {
            session.setAttribute(SessionAttribute.LOCALE, request.getServletContext().getInitParameter(DEFAULT_LOCALE_PARAM));
        }
        Optional<String> requestedLocale = Optional.ofNullable(request.getParameter(RequestParameter.LOCALE));
        if (requestedLocale.isPresent()) {
            session.setAttribute(SessionAttribute.LOCALE, requestedLocale.get());
            String requestString = removeLocaleParam(request);
            response.sendRedirect(requestString);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String removeLocaleParam(HttpServletRequest request) {
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        StringBuilder requestString = new StringBuilder(request.getContextPath() + "/controller?");
        Set<Map.Entry<String, String[]>> requestParameterSet = requestParameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : requestParameterSet){
            if(!RequestParameter.LOCALE.equals(entry.getKey())) {
                requestString.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("&");
            }
        }
        requestString.deleteCharAt(requestString.length() - 1);
        return requestString.toString();
    }
}
