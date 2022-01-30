package com.epamtc.airline.dao;

/**
 * This class provides a common field that contains SQL query.
 */
public abstract class AbstractQuery {
    private final String query;

    AbstractQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
