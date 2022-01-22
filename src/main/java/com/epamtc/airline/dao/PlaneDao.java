package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Plane;

import java.util.List;

public interface PlaneDao {
    Plane findPlaneByID(long planeID) throws DaoException;
    List<Plane> findAllPlanes() throws DaoException;
    void addPlane(Plane plane) throws DaoException;
    void updatePlane(Plane plane) throws DaoException;
}
