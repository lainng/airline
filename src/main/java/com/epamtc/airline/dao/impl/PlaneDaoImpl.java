package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.PlaneDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Plane;

import java.util.List;

public class PlaneDaoImpl extends AbstractDao<Plane> implements PlaneDao {

    private static final String QUERY_GET_PLANE_BY_ID = "SELECT * FROM plane WHERE plane_id = ?;";
    private static final String QUERY_GET_ALL_PLANES = "SELECT * FROM plane;";
    private static final String QUERY_INSERT_NEW_PLANE = "INSERT INTO plane (model, flying_hours, pass_capacity, flight_range) VALUES (?, ?, ? ,?);";
    private static final String QUERY_UPDATE_PLANE_BY_ID = "UPDATE plane SET model = ?, flying_hours = ?, flight_range = ?, pass_capacity = ? WHERE plane_id = ?;";

    public PlaneDaoImpl() {
        super(EntityBuilderFactory.getInstance().getPlaneBuilder());
    }

    @Override
    public Plane takePlaneByID(long planeID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_PLANE_BY_ID,
                planeID
        );
    }
    @Override
    public List<Plane> takeAllPlanes() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_ALL_PLANES);
    }
    @Override
    public void insertPlane(Plane plane) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_INSERT_NEW_PLANE,
                plane.getModel(),
                plane.getFlyingHours(),
                plane.getFlightRange(),
                plane.getPassengerCapacity()
        );
    }
    @Override
    public void updatePlane(Plane plane) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_PLANE_BY_ID,
                plane.getModel(),
                plane.getFlyingHours(),
                plane.getFlightRange(),
                plane.getPassengerCapacity(),
                plane.getID()
        );
    }
}