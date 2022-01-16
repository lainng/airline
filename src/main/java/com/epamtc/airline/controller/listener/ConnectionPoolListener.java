package com.epamtc.airline.controller.listener;

import com.epamtc.airline.dao.connection.ConnectionPool;
import com.epamtc.airline.dao.exception.ConnectionPoolException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().init();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Unable to initialize connection pool.");
            throw new RuntimeException("Unable to initialize connection pool.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().terminate();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Unable to terminate connection pool.");
            throw new RuntimeException("Unable to terminate connection pool.", e);
        }
    }
}
