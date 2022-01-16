package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.entity.dto.FlightDto;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FlightDtoBuilder implements EntityBuilder<FlightDto> {
    @Override
    public FlightDto build(ResultSet resultSet) throws SQLException {
        FlightDto flightDto = new FlightDto();
        FlightStatusBuilder flightStatusBuilder = EntityBuilderFactory.getInstance().getFlightStatusBuilder();

        flightDto.setID(resultSet.getLong(Column.FLIGHT_ID));
        flightDto.setRouteID(resultSet.getLong(Column.FLIGHT_ROUTE_ID));
        flightDto.setPlaneID(resultSet.getLong(Column.FLIGHT_PLANE_ID));
        flightDto.setDepartureTime(resultSet.getTimestamp(Column.FLIGHT_DEPARTURE_TIME));
        flightDto.setFlightStatus(flightStatusBuilder.build(resultSet));
        if (hasEmployeeConfirmationColumn(resultSet)) {
            flightDto.setConfirmed(resultSet.getBoolean(Column.EMPLOYEE_CONFIRMATION));
        }
        return flightDto;
    }

    private boolean hasEmployeeConfirmationColumn(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            if (Column.EMPLOYEE_CONFIRMATION.equalsIgnoreCase(resultSetMetaData.getColumnName(i))) {
                return true;
            }
        }
        return false;
    }
}
