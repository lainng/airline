package com.epamtc.airline.dao;

import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDao<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    protected QueryExecutor<T> queryExecutor;
    protected EntityBuilder<T> entityBuilder;

    protected AbstractDao(EntityBuilder<T> entityBuilder) {
        this.entityBuilder = entityBuilder;
        queryExecutor = new QueryExecutor<>(this.entityBuilder);
    }
}
