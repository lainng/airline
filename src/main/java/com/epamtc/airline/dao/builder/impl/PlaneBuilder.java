package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.entity.Plane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneBuilder implements EntityBuilder<Plane> {

    public PlaneBuilder() {
    }

    @Override
    public Plane build(ResultSet resultSet) throws SQLException {
        Plane plane = new Plane();
        plane.setID(resultSet.getLong(Column.PLANE_ID));
        plane.setModel(resultSet.getString(Column.PLANE_MODEL));
        plane.setFlyingHours(resultSet.getInt(Column.PLANE_FLYING_HOURS));
        plane.setPassengerCapacity(resultSet.getInt(Column.PLANE_PASSENGER_CAPACITY));
        plane.setFlightRange(resultSet.getInt(Column.PLANE_FLIGHT_RANGE));
        return plane;
    }
}