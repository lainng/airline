package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Plane;

import java.util.List;

public interface PlaneDao {
    Plane takePlaneByID(long planeID) throws DaoException;
    List<Plane> takeAllPlanes() throws DaoException;
    void insertPlane(Plane plane) throws DaoException;
    void updatePlane(Plane plane) throws DaoException;
}
