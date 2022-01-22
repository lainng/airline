package com.epamtc.airline.service;

import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface PlaneService {

    /**
     * @param planeID
     * @return
     * @throws ServiceException
     */
    Optional<Plane> takePlane(long planeID) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<Plane> takeAllPlanes() throws ServiceException;

    /**
     *
     * @param plane
     * @return
     * @throws ServiceException
     */
    boolean createPlane(Plane plane) throws ServiceException;

    /**
     *
     * @param plane
     * @return
     * @throws ServiceException
     */
    boolean editPlane(Plane plane) throws ServiceException;
}