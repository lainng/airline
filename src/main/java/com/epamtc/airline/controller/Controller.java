package com.epamtc.airline.controller;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandResult;
import com.epamtc.airline.command.Pages;
import com.epamtc.airline.command.RequestParameter;
import com.epamtc.airline.command.factory.CommandFactory;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * This class provides HTTP requests and responses servlet.
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null || "".equals(commandName)) {
            request.getRequestDispatcher(Pages.HOME_PAGE).forward(request, response);
        }
        Command command = CommandFactory.getInstance().takeCommand(commandName);
        CommandResult commandResult;
        if(command != null) {
            try {
                commandResult = command.execute(request, response);
                String encodedURL;
                if(commandResult.isForward()) {
                    encodedURL = response.encodeURL(commandResult.getPagePath());
                    request.getRequestDispatcher(encodedURL).forward(request, response);
                } else {
                    encodedURL = response.encodeRedirectURL(commandResult.getPagePath());
                    response.sendRedirect(encodedURL);
                }
            } catch (ServiceException e) {
                LOGGER.error("Unable to execute the command " + commandName, e);
                request.getRequestDispatcher(Pages.ERROR_500).forward(request, response);
            }
        } else {
            LOGGER.error("The \"" + commandName + "\" command is not available");
            request.getRequestDispatcher(Pages.ERROR_404).forward(request, response);
        }
    }
}