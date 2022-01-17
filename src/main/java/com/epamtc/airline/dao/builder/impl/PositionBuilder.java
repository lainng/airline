package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.entity.Position;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionBuilder implements EntityBuilder<Position> {
    @Override
    public Position build(ResultSet resultSet) throws SQLException {
        Position position = new Position();
        position.setID(resultSet.getLong(Column.POSITION_ID));
        position.setName(resultSet.getString(Column.POSITION_NAME));
        position.setRoleID(resultSet.getLong(Column.POSITION_ROLE_ID));
        return position;
    }
}
