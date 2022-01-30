package com.epamtc.airline.dao;

import java.util.List;

/**
 * This class is required for storing and processing batch database queries.
 */
public class BatchQuery extends AbstractQuery {
    private final List<Object[]> batch;

    public BatchQuery(String query, List<Object[]> batch) {
        super(query);
        this.batch = batch;
    }

    public List<Object[]> getBatch() {
        return batch;
    }
}
