package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;

import java.util.List;

public interface CityDao {
    List<City> findAllCities() throws DaoException;
    City findCityByID(long cityID) throws DaoException;
    void addCity(City city) throws DaoException;
    void updateCity(City city) throws DaoException;
}