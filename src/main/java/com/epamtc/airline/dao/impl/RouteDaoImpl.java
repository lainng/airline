package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.RouteDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.RouteDto;

import java.util.List;

public class RouteDaoImpl extends AbstractDao<RouteDto> implements RouteDao {

    private static final String QUERY_GET_ROUTE_BY_ID = "SELECT * FROM route WHERE route_id = ?;";
    private static final String QUERY_GET_ALL_ROUTES = "SELECT * FROM route;";
    private static final String QUERY_INSERT_ROUTE = "INSERT INTO route (departure_city_id, destination_city_id, distance, duration) VALUE (?, ?, ?, ?, ?);";
    private static final String QUERY_UPDATE_ROUTE = "UPDATE route SET departure_city_id = ?, destination_city_id = ?, distance = ?, duration = ? WHERE route_id = ?;";

    public RouteDaoImpl() {
        super(EntityBuilderFactory.getInstance().getRouteDtoBuilder());
    }

    @Override
    public RouteDto takeRouteByID(long routeID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_ROUTE_BY_ID,
                routeID);
    }
    @Override
    public List<RouteDto> takeAllRoutes() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_ALL_ROUTES);
    }
    @Override
    public void insertRoute(RouteDto routeDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_INSERT_ROUTE,
                routeDto.getDepartureID(),
                routeDto.getDestinationID(),
                routeDto.getDistance(),
                routeDto.getDuration()
        );
    }
    @Override
    public void updateRoute(RouteDto routeDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_ROUTE,
                routeDto.getDepartureID(),
                routeDto.getDestinationID(),
                routeDto.getDistance(),
                routeDto.getDuration(),
                routeDto.getID()
        );
    }
}