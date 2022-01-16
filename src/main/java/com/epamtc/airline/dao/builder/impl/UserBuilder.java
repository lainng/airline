package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements EntityBuilder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        PositionBuilder positionBuilder = EntityBuilderFactory.getInstance().getPositionBuilder();
        User user = new User();
        user.setID(resultSet.getLong(Column.EMPLOYEE_ID));
        user.setFirstName(resultSet.getString(Column.EMPLOYEE_FIRST_NAME));
        user.setLastName(resultSet.getString(Column.EMPLOYEE_LAST_NAME));
        user.setPosition(positionBuilder.build(resultSet));
        user.setEmail(resultSet.getString(Column.EMPLOYEE_EMAIL));
        user.setPassword(resultSet.getString(Column.EMPLOYEE_PASSWORD));
        return user;
    }
}
