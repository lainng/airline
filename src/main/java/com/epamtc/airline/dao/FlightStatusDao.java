package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.FlightStatus;

public interface FlightStatusDao {

    /**
     * Fetches flight status by its ID.
     * @param statusID The ID of status in data source.
     * @return An {@link FlightStatus} instance.
     * @throws DaoException if a data source access error or other errors.
     */
    FlightStatus findFlightStatusByID(long statusID) throws DaoException;
}