package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.RouteDto;

import java.util.List;

public interface RouteDao {
    RouteDto takeRouteByID(long routeID) throws DaoException;
    List<RouteDto> takeAllRoutes() throws DaoException;
    void insertRoute(RouteDto routeDto) throws DaoException;
    void updateRoute(RouteDto routeDto) throws DaoException;
}
