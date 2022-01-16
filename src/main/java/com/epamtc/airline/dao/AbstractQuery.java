package com.epamtc.airline.dao;

public abstract class AbstractQuery {
    private final String query;

    AbstractQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
