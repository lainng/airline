package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.entity.dto.RouteDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteDtoBuilder implements EntityBuilder<RouteDto> {
    @Override
    public RouteDto build(ResultSet resultSet) throws SQLException {
        RouteDto routeDto = new RouteDto();
        routeDto.setID(resultSet.getLong(Column.ROUTE_ID));
        routeDto.setDepartureID(resultSet.getLong(Column.ROUTE_DEPARTURE_ID));
        routeDto.setDestinationID(resultSet.getLong(Column.ROUTE_DESTINATION_ID));
        routeDto.setDistance(resultSet.getInt(Column.ROUTE_DISTANCE));
        routeDto.setDuration(resultSet.getTime(Column.ROUTE_DURATION));
        return routeDto;
    }
}
