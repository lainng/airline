package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is required for storing and processing parameters of database queries.
 */
public class StatementParameterSetter {
    private static final Logger LOGGER = LogManager.getLogger();
    private Object[] parameters;
    private List<Object[]> batch;

    public StatementParameterSetter(Object[] parameters) {
        this.parameters = parameters;
    }

    public StatementParameterSetter(List<Object[]> batch) {
        this.batch = batch;
    }

    public void setParametersToStatement(PreparedStatement statement) throws DaoException {
        checkStatementForNull(statement);
        for (int i = 1; i <= parameters.length; i++) {
            try {
                statement.setObject(i, parameters[i - 1]);
            } catch (SQLException e) {
                LOGGER.error("Unable to set the parameter to the statement.");
                throw new DaoException("Unable to set the parameter to the statement.", e);
            }
        }
    }

    public void setBatchToStatement(PreparedStatement statement) throws DaoException {
        checkStatementForNull(statement);
        for (Object[] parameters : batch) {
            try {
                for (int i = 1; i <= parameters.length; i++) {
                    statement.setObject(i, parameters[i - 1]);
                }
                statement.addBatch();
            } catch (SQLException e) {
                LOGGER.error("Unable to set the parameter to the statement.");
                throw new DaoException("Unable to set the parameter to the statement.", e);
            }
        }
    }

    private void checkStatementForNull(PreparedStatement statement) throws DaoException {
        if (statement == null) {
            LOGGER.error("The statement must not be null.");
            throw new DaoException("The statement must not be null.");
        }
    }
}