package com.epamtc.airline.dao.connection;

import com.epamtc.airline.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private Properties properties;
    private static final String URL_PROP = "db.url";
    private static final String USER_PROP = "db.user";
    private static final String PASSWORD_PROP = "db.password";
    private static final String DRIVER_PROP = "db.jdbc-driver";
    private static final String POOL_SIZE = "pool.size";

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> usedConnections;

    private ConnectionPool() {}

    public void init() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties = new Properties();
            properties.load(input);
            Class.forName(properties.getProperty(DRIVER_PROP));
            String dbURL = properties.getProperty(URL_PROP);
            String dbUser = properties.getProperty(USER_PROP);
            String dbPassword = properties.getProperty(PASSWORD_PROP);
            int poolSize = Integer.parseInt(properties.getProperty(POOL_SIZE));
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                freeConnections.add(connection);
            }
        } catch (IOException e) {
            LOGGER.error("Unable to load database properties.", e);
            throw new ConnectionPoolException("Unable to load database properties.", e);
        } catch (SQLException e) {
            LOGGER.error("Unable to connect to database.", e);
            throw new ConnectionPoolException("Unable to connect to database.", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("MySQL JDBC driver not found.", e);
            throw new ConnectionPoolException("MySQL JDBC driver not found.", e);
        }
        LOGGER.info("Connection pool is initialized.");
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Unable to connect to data source.", e);
            throw new ConnectionPoolException("Unable to connect to data source.", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        returnConnection(connection);
    }

    public void releaseConnection(Connection connection, Statement statement) throws ConnectionPoolException {
        returnConnection(connection);
        closeStatement(statement);
    }

    public void releaseConnection(Connection connection, Statement statement, ResultSet resultSet) throws ConnectionPoolException {
        returnConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    private void returnConnection(Connection connection) throws ConnectionPoolException {
        if (connection != null) {
            usedConnections.remove(connection);
            try {
                freeConnections.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("Unable to release connection to data source.", e);
                throw new ConnectionPoolException("Unable to release connection to data source.", e);
            }
        }
    }

    private void closeStatement(Statement statement) throws ConnectionPoolException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close statement", e);
                throw new ConnectionPoolException("Unable to close statement", e);
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) throws ConnectionPoolException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Unable to close result set.", e);
                throw new ConnectionPoolException("Unable to close result set.", e);
            }
        }
    }

    public void terminate() throws ConnectionPoolException {
        disposeConnections();
        deregisterDriver();
    }

    private void disposeConnections() throws ConnectionPoolException {
        try {
            for (Connection connection : freeConnections) {
                connection.close();
            }
            for (Connection connection : usedConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to close all connections.", e);
            throw new ConnectionPoolException("Unable to close all connections.", e);
        }
        LOGGER.info("All connections from connection pool is closed.");
    }

    private void deregisterDriver() {
        Driver driver;
        try {
            driver = DriverManager.getDriver(properties.getProperty(URL_PROP));
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            LOGGER.error("Unable to unregister the JDBC driver", e);
        }
        LOGGER.info("JDBC driver is unregistered.");
    }

    private static class ConnectionPoolHolder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}

