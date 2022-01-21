package com.epamtc.airline.service.impl;

import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.RouteDao;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import com.epamtc.airline.service.validation.RouteValidator;
import com.epamtc.airline.service.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Route> takeAllRoutes() throws ServiceException {
        RouteDao routeDao = DaoFactory.getInstance().getRouteDao();
        List<Route> routes = new ArrayList<>();
        try {
            List<RouteDto> routeDtoList = routeDao.takeAllRoutes();
            for (RouteDto dto : routeDtoList) {
                routes.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get all routes. {}", e.getMessage());
            throw new ServiceException("Unable to get all routes.", e);
        }
        return routes;
    }
    @Override
    public Optional<Route> takeRoute(long routeID) throws ServiceException {
        RouteDao routeDao = DaoFactory.getInstance().getRouteDao();
        Route route;
        try {
            RouteDto routeDto = routeDao.takeRouteByID(routeID);
            route = toEntity(routeDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to get a route by its ID. {}", e.getMessage());
            throw new ServiceException("Unable to get a route by its ID.", e);
        }
        return Optional.ofNullable(route);
    }
    @Override
    public boolean createRoute(RouteDto routeDto) throws ServiceException {
        RouteValidator routeValidator = ValidatorFactory.getInstance().getRouteDtoValidator();
        boolean isEntityValid = routeValidator.validateDto(routeDto);
        if (!isEntityValid) {
            return false;
        }
        RouteDao routeDao = DaoFactory.getInstance().getRouteDao();
        try {
            routeDao.insertRoute(routeDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to create a new route. {}", e.getMessage());
            throw new ServiceException("Unable to create a new route.", e);
        }
        return true;
    }
    @Override
    public boolean editRoute(RouteDto routeDto) throws ServiceException {
        RouteValidator routeValidator = ValidatorFactory.getInstance().getRouteDtoValidator();
        boolean isEntityValid = routeValidator.validateDto(routeDto);
        if (!isEntityValid) {
            return false;
        }
        RouteDao routeDao = DaoFactory.getInstance().getRouteDao();
        try {
            routeDao.updateRoute(routeDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to update a route. {}", e.getMessage());
            throw new ServiceException("Unable to update a route.", e);
        }
        return true;
    }

    private Route toEntity(RouteDto routeDto) throws ServiceException {
        if(routeDto == null) {
            return null;
        }

        Route route = new Route();
        CityService cityService = ServiceFactory.getInstance().getCityService();

        route.setID(routeDto.getID());
        Optional<City> optionalDeparture = cityService.takeCity(routeDto.getDepartureID());
        optionalDeparture.ifPresent(route::setDeparture);
        Optional<City> optionalDestination = cityService.takeCity(routeDto.getDestinationID());
        optionalDestination.ifPresent(route::setDestination);
        route.setDistance(routeDto.getDistance());
        route.setDuration(routeDto.getDuration());

        return route;
    }
}