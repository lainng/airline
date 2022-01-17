package com.epamtc.airline.dao;

import com.epamtc.airline.dao.builder.EntityBuilder;

public class AbstractDao<T> {
    protected QueryExecutor<T> queryExecutor;

    protected AbstractDao(EntityBuilder<T> entityBuilder) {
        queryExecutor = new QueryExecutor<>(entityBuilder);
    }
}