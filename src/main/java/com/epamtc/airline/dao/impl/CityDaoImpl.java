package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.CityDao;
import com.epamtc.airline.dao.builder.impl.CityBuilder;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;

import java.util.List;

public class CityDaoImpl extends AbstractDao<City> implements CityDao {
    private static final String QUERY_GET_CITY_BY_ID = "SELECT * FROM city WHERE city_id = ?;";
    private static final String QUERY_GET_ALL_CITIES = "SELECT * FROM city;";
    private static final String QUERY_INSERT_CITY = "INSERT INTO city (name) VALUE (?);";
    private static final String QUERY_UPDATE_CITY = "UPDATE city SET name = ? WHERE city_id = ?;";

    public CityDaoImpl() {
        super(new CityBuilder());
    }

    @Override
    public List<City> takeAllCities() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_ALL_CITIES);
    }
    @Override
    public City takeCityByID(long cityID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_CITY_BY_ID,
                cityID);
    }
    @Override
    public void insertCity(City city) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_INSERT_CITY,
                city.getName()
        );
    }
    @Override
    public void updateCity(City city) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_CITY,
                city.getName(),
                city.getID()
        );
    }
}