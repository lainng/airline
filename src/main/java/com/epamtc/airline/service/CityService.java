package com.epamtc.airline.service;

import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CityService {

    /**
     * Retrieves a city's entity by a specified ID.
     * @param cityID The ID of the city.
     * @return A {@link Optional} describing city entity, or an empty {@link Optional} if the city is not found.
     * @throws ServiceException if retrieving of the city is impossible.
     */
    Optional<City> takeCity(long cityID) throws ServiceException;

    /**
     * Retrieves all cities.
     * @return The {@link List} of cities or the empty {@link List} if cities is not found.
     * @throws ServiceException if retrieving of the city's list is impossible.
     */
    List<City> takeAllCities() throws ServiceException;

    /**
     * Creates city's entity from the data source.
     * @param city A {@link City} entity.
     * @return {@code true} if the entity created or {@code false} if the entity is not valid.
     * @throws ServiceException if creating a city's entity is impossible.
     */
    boolean createCity(City city) throws ServiceException;

    /**
     * Edited city's entity from the data source.
     * @param city A {@link City} entity.
     * @return {@code true} if the entity edited or {@code false} if the entity is not valid.
     * @throws ServiceException if editing a city's entity is impossible.
     */
    boolean editCity(City city) throws ServiceException;
}
