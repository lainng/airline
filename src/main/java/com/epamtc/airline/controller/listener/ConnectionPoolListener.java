package com.epamtc.airline.controller.listener;

import com.epamtc.airline.dao.connection.ConnectionPool;
import com.epamtc.airline.dao.exception.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides a web listener that initialized the pool of database connections.
 */
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
