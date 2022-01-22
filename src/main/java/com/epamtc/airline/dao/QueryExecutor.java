package com.epamtc.airline.dao;

import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.dao.connection.ConnectionPool;
import com.epamtc.airline.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor<T> {
    private static final ConnectionPool POOL = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();
    protected EntityBuilder<T> builder;

    public QueryExecutor(EntityBuilder<T> builder) {
        this.builder = builder;
    }

    public List<T> executeQuery(String query, Object... parameters) throws DaoException {
        List<T> result = new ArrayList<>();
        Connection connection = POOL.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StatementParameterSetter parameterSetter = new StatementParameterSetter(parameters);
            parameterSetter.setParametersToStatement(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(builder.build(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid SQL statement. {}", e.getMessage());
            throw new DaoException("Invalid SQL statement.", e);
        } finally {
            POOL.releaseConnection(connection, statement, resultSet);
        }
        return result;
    }

    public T executeSingleEntityQuery(String query, Object... parameters) throws DaoException {
        return executeQuery(query, parameters)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void executeUpdate(String query, Object... parameters) throws DaoException {
        Connection connection = POOL.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query);
            StatementParameterSetter parameterSetter = new StatementParameterSetter(parameters);
            parameterSetter.setParametersToStatement(statement);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollbackChanges(connection);
            LOGGER.error("Invalid SQL statement. {}", e.getMessage());
            throw new DaoException("Invalid SQL statement.", e);
        } finally {
            switchAutoCommit(connection);
            POOL.releaseConnection(connection, statement);
        }
    }

    public void executeTransactionUpdate(AbstractQuery... queries) throws DaoException {
        Connection connection = POOL.getConnection();
        try {
            connection.setAutoCommit(false);
            for (AbstractQuery query : queries) {
                if (query instanceof BasicQuery) {
                    executeUpdates(connection, (BasicQuery) query);
                } else {
                    executeBatch(connection, (BatchQuery) query);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            rollbackChanges(connection);
            LOGGER.error("Invalid SQL statement. {}", e.getMessage());
            throw new DaoException("Invalid SQL statement.", e);
        } finally {
            switchAutoCommit(connection);
            POOL.releaseConnection(connection);
        }
    }

    private void executeUpdates(Connection connection, BasicQuery query) throws SQLException, DaoException {
        PreparedStatement statement = connection.prepareStatement(query.getQuery());
        StatementParameterSetter parameterSetter = new StatementParameterSetter(query.getParameters());
        parameterSetter.setParametersToStatement(statement);
        statement.executeUpdate();
    }

    private void executeBatch(Connection connection, BatchQuery query) throws SQLException, DaoException {
        PreparedStatement statement = connection.prepareStatement(query.getQuery());
        StatementParameterSetter parameterSetter = new StatementParameterSetter(query.getBatch());
        parameterSetter.setBatchToStatement(statement);
        statement.executeBatch();
    }

    private void rollbackChanges(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Error while rolling back transaction. {}", e.getMessage());
                throw new DaoException("Error while rolling back transaction.", e);
            }
        }
    }

    private void switchAutoCommit(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error("Unable to switch auto commit for transaction. {}", e.getMessage());
                throw new DaoException("Unable to switch auto commit for transaction.", e);
            }
        }
    }
}