package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Position;

import java.util.List;

public interface PositionDao {

    Position takePositionByID(long positionID) throws DaoException;

    /**
     * Fetches {@link List} of {@link Position} entities that user can occupy.
     * @return {@link List} of {@link Position} entities that user can occupy, or empty {@link List} if positions are not found.
     * @throws DaoException if a data source access error or other errors.
     */
    List<Position> takeAllPositions() throws DaoException;
}
