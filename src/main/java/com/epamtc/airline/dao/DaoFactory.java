package com.epamtc.airline.dao;

import com.epamtc.airline.dao.impl.*;

public class DaoFactory {
    private final UserDao userDao = new UserDaoImpl();
    private final PlaneDao planeDao = new PlaneDaoImpl();
    private final RouteDao routeDao = new RouteDaoImpl();
    private final FlightDao flightDao = new FlightDaoImpl();
    private final CityDao cityDao = new CityDaoImpl();
    private final PositionDao positionDao = new PositionDaoImpl();
    private final CrewDao crewDao = new CrewDaoImpl();

    private static class Holder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }
    public PlaneDao getPlaneDao() { return planeDao; }
    public RouteDao getRouteDao() { return routeDao; }
    public FlightDao getFlightDao() { return flightDao; }
    public CityDao getCityDao() {
        return cityDao;
    }
    public PositionDao getPositionDao() {
        return positionDao;
    }
    public CrewDao getCrewDao() {
        return crewDao;
    }
}