package com.epamtc.airline.service;

import com.epamtc.airline.service.impl.*;

public class ServiceFactory {
    private final UserService userService = new UserServiceImpl();
    private final FlightService flightService = new FlightServiceImpl();
    private final RouteService routeService = new RouteServiceImpl();
    private final PlaneService planeService = new PlaneServiceImpl();
    private final CityService cityService = new CityServiceImpl();
    private final CrewService crewService = new CrewServiceImpl();
    private final MailService mailService = new MailServiceImpl();

    private static class Holder{
        static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }
    public FlightService getFlightService() {
        return flightService;
    }
    public RouteService getRouteService() {
        return routeService;
    }
    public PlaneService getPlaneService() {
        return planeService;
    }
    public CityService getCityService() {
        return cityService;
    }
    public CrewService getCrewService() {
        return crewService;
    }
    public MailService getMailService() {
        return mailService;
    }
}