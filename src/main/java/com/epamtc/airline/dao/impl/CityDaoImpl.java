package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.CityDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;

import java.util.List;

public class CityDaoImpl extends AbstractDao<City> implements CityDao {
    private static final String QUERY_GET_CITY_BY_ID = "SELECT * FROM city WHERE city_id = ?;";
    private static final String QUERY_GET_ALL_CITIES = "SELECT * FROM city;";
    private static final String QUERY_INSERT_CITY = "INSERT INTO city (name) VALUE (?);";
    private static final String QUERY_UPDATE_CITY = "UPDATE city SET name = ? WHERE city_id = ?;";

    public CityDaoImpl() {
        super(EntityBuilderFactory.getInstance().getCityBuilder());
    }

    @Override
    public List<City> findAllCities() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_ALL_CITIES);
    }
    @Override
    public City findCityByID(long cityID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_CITY_BY_ID,
                cityID);
    }
    @Override
    public void addCity(City city) throws DaoException {
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