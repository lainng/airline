package com.epamtc.airline.service;

import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CityService {

    /**
     *
     * @param cityID
     * @return
     * @throws ServiceException
     */
    Optional<City> takeCity(long cityID) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<City> takeAllCities() throws ServiceException;

    /**
     *
     * @param city
     * @return
     * @throws ServiceException
     */
    boolean createCity(City city) throws ServiceException;

    /**
     *
     * @param city
     * @return
     * @throws ServiceException
     */
    boolean editCity(City city) throws ServiceException;
}
