package com.epamtc.airline.dao;

import com.epamtc.airline.command.RequestParameterValidator;
import com.epamtc.airline.dao.connection.ConnectionPool;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.dao.impl.*;
import com.epamtc.airline.entity.*;
import com.epamtc.airline.entity.dto.CrewDto;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.RouteDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryExecutorTest {
    /*PlaneDao planeDao = new PlaneDaoImpl();
    RouteDao routeDao = new RouteDaoImpl();
    CityDao cityDao = new CityDaoImpl();
    FlightDao flightDao = new FlightDaoImpl();
    CrewDao crewDao = new CrewDaoImpl();

    @BeforeAll
    static void setUp() {
        ConnectionPool.getInstance().init();
    }

    @AfterAll
    static void tearDow() {
        ConnectionPool.getInstance().terminate();
    }

    @Test
    void buildSingleEntityTest() throws DaoException {
        Plane expectedPlane = new Plane();
        expectedPlane.setID(1L);
        expectedPlane.setModel("Boeing 737-100");
        expectedPlane.setPassengerCapacity(103);
        expectedPlane.setFlyingHours(1344);
        expectedPlane.setFlightRange(2592);

        RouteDto expectedRoute = new RouteDto();
        expectedRoute.setID(13L);
        expectedRoute.setNumber("WW932");
        expectedRoute.setDepartureID(1L);
        expectedRoute.setDestinationID(2L);
        expectedRoute.setDistance(713);

        City expectedCity = new City();
        expectedCity.setID(1L);
        expectedCity.setName("Минск");

        FlightDto expectedFlight = new FlightDto();
        expectedFlight.setID(9L);
        expectedFlight.setRouteID(17L);
        expectedFlight.setPlaneID(3L);
        FlightStatus fs = new FlightStatus();
        fs.setID(1L);
        expectedFlight.setFlightStatus(fs);
        expectedFlight.setDepartureTime(new Timestamp(1640242800000L));
        expectedFlight.setConfirmed(true);

        CrewDto expectedCrew = new CrewDto();
        expectedCrew.setID(3L);
        expectedCrew.setAssignedFlightID(9L);

        List<CrewDto> actualListCrews = crewDao.findAllCrews();
        City actualCity = cityDao.takeCityByID(1L);
        Plane actualPlane = planeDao.takePlaneByID(99L);
        RouteDto actualRoute = routeDao.takeRouteByID(13L);
        List<FlightDto> actualFlight = flightDao.findUserFlights(53L);
        List<FlightDto> actualList = flightDao.findUnassignedFlights();
        *//*assertEquals(expectedPlane, actualPlane);*//*
        *//*assertEquals(expectedRoute, actualRoute);*//*
        *//*assertEquals(expectedCity, actualCity);*//*
        *//*assertEquals(expectedCrew.getAssignedFlightID(), actualListCrews.get(1).getAssignedFlightID());*//*
    }

    @Test
    void buildEntityListTest() throws DaoException {
        Plane expectedPlane = new Plane();
        expectedPlane.setID(1L);
        expectedPlane.setModel("Boeing 737-100");
        expectedPlane.setPassengerCapacity(103);
        expectedPlane.setFlyingHours(1344);
        expectedPlane.setFlightRange(2592);

        RouteDto expectedRoute = new RouteDto();
        expectedRoute.setID(13L);
        expectedRoute.setNumber("WW932");
        expectedRoute.setDepartureID(1L);
        expectedRoute.setDestinationID(2L);
        expectedRoute.setDistance(713);

        List<Plane> actualPlanes = planeDao.takeAllPlanes();
        List<RouteDto> actualRoutes = routeDao.takeAllRoutes();
        *//*assertEquals(expectedPlane, actualPlanes.get(0));*//*
        assertEquals(expectedRoute, actualRoutes.get(0));
    }

    @Test
    void insertEntityTest() throws DaoException {
        Route expectedRoute = new Route();
        expectedRoute.setID(32L);
        expectedRoute.setNumber("WW007");
        City dept = new City();
        dept.setID(1L);
        expectedRoute.setDeparture(dept);
        expectedRoute.setDestination(dept);
        expectedRoute.setDistance(9999);
        expectedRoute.setDuration(Time.valueOf("12:11:11"));

        Flight expectedFlight = new Flight();
        expectedFlight.setID(18L);
        Route route = new Route();
        route.setID(13L);
        Plane plane = new Plane();
        plane.setID(1L);
        FlightStatus fs = new FlightStatus();
        fs.setID(1L);
        expectedFlight.setPlane(plane);
        expectedFlight.setRoute(route);
        expectedFlight.setFlightStatus(fs);
        expectedFlight.setDepartureTime(new Timestamp(1640978542000L));
        FlightDto actualFlight = flightDao.findFlightByID(18L);
        assertEquals(expectedFlight.getID(), actualFlight.getID());
    }

    *//*@Test
    void updateCrewTest() throws DaoException {
        crewDao.updateCrew(9L, 15L, new long[] {1L, 3L});
        CrewDto actualCrew = crewDao.findCrewByFlightID(15L);
        assertEquals(1L, actualCrew.getMembers().get(0).getID());
    }*//*

    *//*@Test
    void insertNewCrewTest() throws DaoException {
        crewDao.insertNewCrew(15L, new long[] {1L, 2L, 3L});
        CrewDto actualCrew = crewDao.findCrewByFlightID(15L);
        assertEquals(1L, actualCrew.getMembers().get(0).getID());
    }*//*

    @Test
    void isNumericTest() {
        RequestParameterValidator validator = new RequestParameterValidator();
        assertTrue(validator.isNumeric("12"));
        assertTrue(validator.isNumeric("-12"));
        assertTrue(validator.isNumeric("0"));

        assertFalse(validator.isNumeric(""));
        assertFalse(validator.isNumeric("sfdls"));

    }

    @Test
    void deleteCrewTest() throws DaoException {
        long actual = crewDao.deleteCrew(14L);
        System.out.println(actual);
        assertEquals(0, actual);
    }*/
}
