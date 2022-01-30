package com.epamtc.airline.dao;

/**
 * This class is required for storing and processing regular database queries.
 */
public class BasicQuery extends AbstractQuery{
    private final Object[] parameters;

    public BasicQuery(String query, Object... parameters) {
        super(query);
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
