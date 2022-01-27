package com.epamtc.airline.controller.filter;

import com.epamtc.airline.command.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthFilter implements Filter {
    private final Map<Long, List<String>> authorizedCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        initCommands();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Long roleID = (Long) session.getAttribute(SessionAttribute.ROLE_ID);
        String command = request.getParameter(RequestParameter.COMMAND);
        if(roleID == null) {
            session.setAttribute(SessionAttribute.ROLE_ID, UserRole.GUEST);
            roleID = UserRole.GUEST;
        }
        if (!authorizedCommands.get(roleID).contains(command)) {
            if (roleID == UserRole.GUEST) {
                response.sendRedirect(Pages.LOGIN_PAGE_REDIRECT);
            } else {
                response.sendRedirect(Pages.ERROR_404_REDIRECT);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void initCommands() {
        authorizedCommands.put(UserRole.GUEST, Arrays.asList(
                CommandName.LOGIN_COMMAND,
                CommandName.LOGIN_PAGE_COMMAND,
                CommandName.HOME_PAGE_COMMAND,
                CommandName.SEARCH_RESULTS_PAGE,
                CommandName.REGISTERED_PAGE_COMMAND,
                CommandName.SIGN_UP_PAGE_COMMAND,
                CommandName.SIGN_UP_COMMAND,
                CommandName.CONTACTS_COMMAND,
                CommandName.ABOUT_COMPANY_COMMAND,
                CommandName.ERROR_404_COMMAND,
                CommandName.SEARCHING_FLIGHT_COMMAND
        ));
        authorizedCommands.put(UserRole.USER, Arrays.asList(
                CommandName.USER_PAGE_COMMAND,
                CommandName.FLIGHT_INFO_COMMAND,
                CommandName.LOGOUT_COMMAND,
                CommandName.HOME_PAGE_COMMAND,
                CommandName.SEARCH_RESULTS_PAGE,
                CommandName.CONFIRM_FLIGHT_COMMAND,
                CommandName.CONTACTS_COMMAND,
                CommandName.ABOUT_COMPANY_COMMAND,
                CommandName.ERROR_404_COMMAND,
                CommandName.SETTINGS_COMMAND,
                CommandName.CHANGE_PASSWORD_COMMAND,
                CommandName.SEARCHING_FLIGHT_COMMAND
        ));
        authorizedCommands.put(UserRole.DISPATCHER, Arrays.asList(
                CommandName.DISPATCHER_PAGE_COMMAND,
                CommandName.DISPATCHER_CREWS_PAGE_COMMAND,
                CommandName.DISPATCHER_FLIGHTS_PAGE_COMMAND,
                CommandName.DISPATCHER_STAFF_PAGE_COMMAND,
                CommandName.CREW_ACTION_PAGE_COMMAND,
                CommandName.DELETE_CREW_COMMAND,
                CommandName.ADD_CREW_COMMAND,
                CommandName.CREWS_PAGE_COMMAND,
                CommandName.FLIGHT_INFO_COMMAND,
                CommandName.LOGOUT_COMMAND,
                CommandName.HOME_PAGE_COMMAND,
                CommandName.SEARCH_RESULTS_PAGE,
                CommandName.CONTACTS_COMMAND,
                CommandName.ABOUT_COMPANY_COMMAND,
                CommandName.ERROR_404_COMMAND,
                CommandName.SETTINGS_COMMAND,
                CommandName.CHANGE_PASSWORD_COMMAND,
                CommandName.SEARCHING_FLIGHT_COMMAND
        ));
        authorizedCommands.put(UserRole.ADMIN, Arrays.asList(
                CommandName.CREWS_PAGE_COMMAND,
                CommandName.FLIGHT_INFO_COMMAND,
                CommandName.LOGOUT_COMMAND,
                CommandName.HOME_PAGE_COMMAND,
                CommandName.SEARCH_RESULTS_PAGE,
                CommandName.CONTACTS_COMMAND,
                CommandName.ABOUT_COMPANY_COMMAND,
                CommandName.ERROR_404_COMMAND,
                CommandName.SETTINGS_COMMAND,
                CommandName.CHANGE_PASSWORD_COMMAND,
                CommandName.ADMIN_PAGE_COMMAND,
                CommandName.STAFF_PAGE_COMMAND,
                CommandName.STAFF_ACTION_PAGE_COMMAND,
                CommandName.ROUTES_PAGE_COMMAND,
                CommandName.ROUTE_ACTION_PAGE_COMMAND,
                CommandName.PLANES_PAGE_COMMAND,
                CommandName.PLANE_ACTION_PAGE_COMMAND,
                CommandName.FLIGHTS_PAGE_COMMAND,
                CommandName.FLIGHT_ACTION_PAGE_COMMAND,
                CommandName.ADD_PLANE_COMMAND,
                CommandName.CITIES_PAGE_COMMAND,
                CommandName.CITY_ACTION_PAGE_COMMAND,
                CommandName.ADD_CITY_COMMAND,
                CommandName.ADD_ROUTE_COMMAND,
                CommandName.EDIT_EMPLOYEE_COMMAND,
                CommandName.CANCEL_FLIGHT_COMMAND,
                CommandName.ADD_FLIGHT_COMMAND,
                CommandName.SEARCHING_FLIGHT_COMMAND
        ));
    }
}
