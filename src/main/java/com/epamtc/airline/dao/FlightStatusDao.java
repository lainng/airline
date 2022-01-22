package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.FlightStatus;

public interface FlightStatusDao {

    /**
     *
     * @param statusID
     * @return
     * @throws DaoException
     */
    FlightStatus findFlightStatusByID(long statusID) throws DaoException;
}