package com.epamtc.airline.service;

import com.epamtc.airline.entity.Route;
import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    /**
     * Retrieves all routes.
     * @return The {@link List} of routes or the empty {@link List} if routes is not found.
     * @throws ServiceException if retrieving of the route's list is impossible.
     */
    List<Route> takeAllRoutes() throws ServiceException;

    /**
     * Retrieves a route's entity by a specified ID.
     * @param routeID The ID of the route.
     * @return A {@link Optional} describing route entity, or an empty {@link Optional} if the route is not found.
     * @throws ServiceException if retrieving of the route is impossible.
     */
    Optional<Route> takeRoute(long routeID) throws ServiceException;

    /**
     * Creates route's entity from the data source.
     * @param routeDto A {@link RouteDto} entity that contains raw information about creating entity.
     * @return {@code true} if the entity created or {@code false} if the route's entity is not valid or the route already created.
     * @throws ServiceException if creating a route's entity is impossible.
     */
    boolean createRoute(RouteDto routeDto) throws ServiceException;

    /**
     * Edited route's entity from the data source.
     * @param routeDto A {@link RouteDto} entity that contains raw information about creating entity.
     * @return {@code true} if the entity edited or {@code false} if the route's entity is not valid or the route already created.
     * @throws ServiceException if editing a route's entity is impossible.
     */
    boolean editRoute(RouteDto routeDto) throws ServiceException;
}