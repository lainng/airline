package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.entity.FlightStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightStatusBuilder implements EntityBuilder<FlightStatus> {
    @Override
    public FlightStatus build(ResultSet resultSet) throws SQLException {
        FlightStatus flightStatus = new FlightStatus();
        flightStatus.setID(resultSet.getLong(Column.FLIGHT_STATUS_ID));
        flightStatus.setName(resultSet.getString(Column.STATUS_NAME));
        return flightStatus;
    }
}
