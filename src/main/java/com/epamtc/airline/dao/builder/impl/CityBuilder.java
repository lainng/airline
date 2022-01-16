package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityBuilder implements EntityBuilder<City> {
    @Override
    public City build(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setID(resultSet.getLong(Column.CITY_ID));
        city.setName(resultSet.getString(Column.CITY_NAME));
        return city;
    }
}
