package com.epamtc.airline.service;

import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface PlaneService {

    /**
     * Retrieves a plane's entity by a specified ID.
     * @param planeID The ID of the plane.
     * @return A {@link Optional} describing plane entity, or an empty {@link Optional} if the plane is not found.
     * @throws ServiceException if retrieving of the plane is impossible.
     */
    Optional<Plane> takePlane(long planeID) throws ServiceException;

    /**
     * Retrieves all planes.
     * @return The {@link List} of planes or the empty {@link List} if planes is not found.
     * @throws ServiceException if retrieving of the plane's list is impossible.
     */
    List<Plane> takeAllPlanes() throws ServiceException;

    /**
     * Creates plane's entity from the data source.
     * @param plane A {@link Plane} entity.
     * @return {@code true} if the entity created or {@code false} if the entity is not valid.
     * @throws ServiceException if creating a plane's entity is impossible.
     */
    boolean createPlane(Plane plane) throws ServiceException;

    /**
     * Edited plane's entity from the data source.
     * @param plane A {@link Plane} entity.
     * @return {@code true} if the entity edited or {@code false} if the entity is not valid.
     * @throws ServiceException if editing a plane's entity is impossible.
     */
    boolean editPlane(Plane plane) throws ServiceException;
}