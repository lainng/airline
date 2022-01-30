package com.epamtc.airline.dao;

import com.epamtc.airline.dao.builder.EntityBuilder;

/**
 * This class provides a common {@link QueryExecutor} field for classes that extends this class.
 * @param <T> The type of the entities that may be fetched from data source.
 */
public class AbstractDao<T> {
    protected QueryExecutor<T> queryExecutor;

    protected AbstractDao(EntityBuilder<T> entityBuilder) {
        queryExecutor = new QueryExecutor<>(entityBuilder);
    }
}