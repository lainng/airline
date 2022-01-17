package com.epamtc.airline.dao.builder;


import com.epamtc.airline.dao.builder.impl.*;

public class EntityBuilderFactory {
    private final CityBuilder cityBuilder = new CityBuilder();
    private final FlightDtoBuilder flightDtoBuilder = new FlightDtoBuilder();
    private final PlaneBuilder planeBuilder = new PlaneBuilder();
    private final RouteDtoBuilder routeDtoBuilder = new RouteDtoBuilder();
    private final UserBuilder userBuilder = new UserBuilder();
    private final FlightStatusBuilder flightStatusBuilder = new FlightStatusBuilder();
    private final PositionBuilder positionBuilder = new PositionBuilder();
    private final CrewDtoBuilder crewBuilder = new CrewDtoBuilder();

    private static class Holder {
        static final EntityBuilderFactory INSTANCE = new EntityBuilderFactory();
    }

    private EntityBuilderFactory() {}

    public static EntityBuilderFactory getInstance() {
        return Holder.INSTANCE;
    }

    public CityBuilder getCityBuilder() {
        return cityBuilder;
    }
    public FlightDtoBuilder getFlightDtoBuilder() {
        return flightDtoBuilder;
    }
    public PlaneBuilder getPlaneBuilder() {
        return planeBuilder;
    }
    public RouteDtoBuilder getRouteDtoBuilder() {
        return routeDtoBuilder;
    }
    public UserBuilder getUserBuilder() {
        return userBuilder;
    }
    public FlightStatusBuilder getFlightStatusBuilder() {
        return flightStatusBuilder;
    }
    public PositionBuilder getPositionBuilder() {
        return positionBuilder;
    }
    public CrewDtoBuilder getCrewDtoBuilder() {
        return crewBuilder;
    }
}