package com.epamtc.airline.service;

import com.epamtc.airline.entity.Route;
import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<Route> takeAllRoutes() throws ServiceException;

    /**
     *
     * @param routeID
     * @return
     * @throws ServiceException
     */
    Optional<Route> takeRoute(long routeID) throws ServiceException;

    /**
     *
     * @param routeDto
     * @return
     * @throws ServiceException
     */
    boolean createRoute(RouteDto routeDto) throws ServiceException;

    /**
     *
     * @param routeDto
     * @return
     * @throws ServiceException
     */
    boolean editRoute(RouteDto routeDto) throws ServiceException;
}