package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;

import java.util.List;

public interface CityDao {

    /**
     * Fetches the list of all cities that contains in the data source.
     * @return The {@link List} of {@link City} entities, or an empty {@link List} if there are no scheduled flights.
     * @throws DaoException if a data source access error or other errors.
     */
    List<City> findAllCities() throws DaoException;

    /**
     * Fetches the city by its ID.
     * @param cityID The ID of the city.
     * @return An {@link City} entity that linked with specified ID or {@code null} if there are no city at data source.
     * @throws DaoException if a data source access error or other errors.
     */
    City findCityByID(long cityID) throws DaoException;

    /**
     * Adds the city to the data source.
     * @param city An {@link City} entity.
     * @throws DaoException if a data source access error or other errors.
     */
    void addCity(City city) throws DaoException;

    /**
     * Updates the city at the data source.
     * @param city An {@link City} entity that contains updated information.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateCity(City city) throws DaoException;
}