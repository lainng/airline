package com.epamtc.airline.dao;

import com.epamtc.airline.dao.builder.EntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractDao<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    protected QueryExecutor<T> queryExecutor;
    protected EntityBuilder<T> entityBuilder;

    protected AbstractDao(EntityBuilder<T> entityBuilder) {
        this.entityBuilder = entityBuilder;
        queryExecutor = new QueryExecutor<>(this.entityBuilder);
    }
}
