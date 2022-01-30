package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Plane;

import java.util.List;

public interface PlaneDao {

    /**
     * Fetches the plane by its ID.
     * @param planeID The plane ID in the data source.
     * @return An {@link Plane} instance that linked with specified ID or {@code null} if no plane are found.
     * @throws DaoException if a data source access error or other errors.
     */
    Plane findPlaneByID(long planeID) throws DaoException;

    /**
     * Fetches the list of all planes.
     * @return The {@link List} of the {@link Plane} that contains in the data source or an empty {@link List} if there are no planes.
     * @throws DaoException if a data source access error or other errors.
     */
    List<Plane> findAllPlanes() throws DaoException;

    /**
     * Adds plane to the data source.
     * @param plane An {@link Plane} entity.
     * @throws DaoException if a data source access error or other errors.
     */
    void addPlane(Plane plane) throws DaoException;

    /**
     * Updates plane information in the data source.
     * @param plane An {@link Plane} entity that contains changing information.
     * @throws DaoException if a data source access error or other errors.
     */
    void updatePlane(Plane plane) throws DaoException;
}
