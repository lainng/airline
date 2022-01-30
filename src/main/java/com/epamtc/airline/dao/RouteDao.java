package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.RouteDto;

import java.util.List;

public interface RouteDao {

    /**
     * Fetches a route DTO by its ID.
     * @param routeID The route ID in the data source.
     * @return An {@link RouteDto} instance that contains raw information of route.
     * @throws DaoException if a data source access error or other errors.
     */
    RouteDto findRouteByID(long routeID) throws DaoException;

    /**
     * Fetches all routes DTOs
     * @return The {@link List} of the {@link RouteDto} that contains in the data source.
     * @throws DaoException if a data source access error or other errors.
     */
    List<RouteDto> findAllRoutes() throws DaoException;

    /**
     * Added the route to the data source.
     * @param routeDto An {@link RouteDto} instance that contains raw information of route.
     * @throws DaoException if a data source access error or other errors.
     */
    void addRoute(RouteDto routeDto) throws DaoException;

    /**
     * Updated route information.
     * @param routeDto An {@link RouteDto} instance that contains raw information of route.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateRoute(RouteDto routeDto) throws DaoException;
}