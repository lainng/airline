package com.epamtc.airline.dao.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface is required to work with entity mapping from SQL queries.
 * @param <T> The type of the entities that may be mapped from SQL query.
 */
public interface EntityBuilder<T> {
    T build(ResultSet resultSet) throws SQLException;
}
