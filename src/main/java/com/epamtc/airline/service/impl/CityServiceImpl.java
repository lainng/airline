package com.epamtc.airline.service.impl;

import com.epamtc.airline.dao.CityDao;
import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.exception.ServiceException;
import com.epamtc.airline.service.validation.CityValidator;
import com.epamtc.airline.service.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<City> takeCity(long cityID) throws ServiceException {
        CityDao cityDao = DaoFactory.getInstance().getCityDao();
        City city;
        try {
            city = cityDao.findCityByID(cityID);
        } catch (DaoException e) {
            LOGGER.error("Unable to get a city by its ID. {}", e.getMessage());
            throw new ServiceException("Unable to get a city by its ID.", e);
        }
        return Optional.ofNullable(city);
    }

    @Override
    public List<City> takeAllCities() throws ServiceException {
        CityDao cityDao = DaoFactory.getInstance().getCityDao();
        List<City> cities;
        try {
            cities = cityDao.findAllCities();
        } catch (DaoException e) {
            LOGGER.error("Unable to get all cities. {}", e.getMessage());
            throw new ServiceException("Unable to get all cities.", e);
        }
        return cities;
    }

    @Override
    public boolean createCity(City city) throws ServiceException {
        CityValidator validator = ValidatorFactory.getInstance().getCityValidator();
        CityDao cityDao = DaoFactory.getInstance().getCityDao();
        if (!validator.validate(city) || !checkUniqueness(city)) {
            return false;
        }
        try {
            cityDao.addCity(city);
        } catch (DaoException e) {
            LOGGER.error("Unable to create a new city. {}", e.getMessage());
            throw new ServiceException("Unable to create a new city.", e);
        }
        return true;
    }

    @Override
    public boolean editCity(City city) throws ServiceException {
        CityValidator validator = ValidatorFactory.getInstance().getCityValidator();
        CityDao cityDao = DaoFactory.getInstance().getCityDao();
        if (!validator.validate(city) || !checkUniqueness(city)) {
            return false;
        }
        try {
            cityDao.updateCity(city);
        } catch (DaoException e) {
            LOGGER.error("Unable to update a new city. {}", e.getMessage());
            throw new ServiceException("Unable to update a new city.", e);
        }
        return true;
    }

    private boolean checkUniqueness(City current) throws ServiceException {
        List<City> cities = takeAllCities();
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(current.getName())) {
                return false;
            }
        }
        return true;
    }
}