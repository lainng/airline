package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;

import java.util.List;

public interface CityDao {
    List<City> takeAllCities() throws DaoException;
    City takeCityByID(long cityID) throws DaoException;
    void insertCity(City city) throws DaoException;
    void updateCity(City city) throws DaoException;
}
