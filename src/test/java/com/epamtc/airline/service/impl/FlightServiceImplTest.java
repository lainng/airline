package com.epamtc.airline.service.impl;

import com.epamtc.airline.ConnectionPoolExtension;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(ConnectionPoolExtension.class)
public class FlightServiceImplTest {
    private final FlightService flightService = new FlightServiceImpl();
    private static final long USER_ID_WITH_ASSIGNED_FLIGHT = 59L;
    private static final long DISPATCHER_ID = 61L;
    private static final long USER_ID_WITHOUT_ASSIGNED_FLIGHT = 62L;
    private static final long USER_POLE_ID = 1L;
    private static final long DISPATCHER_POLE_ID = 2L;
    private static final long EXISTING_FLIGHT_ID = 24L;
    private static final long INVALID_FLIGHT_ID = -24L;
    private static final long SCHEDULED_STATUS_ID = 1L;
    private static final long INVALID_STATUS_ID = -1L;

    @Test
    void takeFlightByExistingIDTest() throws ServiceException {
        Optional<Flight> actual = flightService.takeFlight(EXISTING_FLIGHT_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_FLIGHT_ID, actual.get().getID());
    }

    @Test
    void takeFlightByInvalidIDTest() throws ServiceException {
        Optional<Flight> actual = flightService.takeFlight(INVALID_FLIGHT_ID);
        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    void takeFlightStatusByExistingIDTest() throws ServiceException {
        FlightStatus actual = flightService.takeFlightStatus(SCHEDULED_STATUS_ID);
        Assertions.assertEquals(SCHEDULED_STATUS_ID, actual.getID());
    }

    @Test
    void takeFlightStatusByInvalidIDTest() throws ServiceException {
        FlightStatus actual = flightService.takeFlightStatus(INVALID_STATUS_ID);
        Assertions.assertNull(actual);
    }

    @Test
    void takeUserFlightByUserWithAssignedFlightTest() throws ServiceException {
        Position userPosition = new Position();
        userPosition.setRoleID(USER_POLE_ID);

        User userWithAssignedFlight = new User();
        userWithAssignedFlight.setID(USER_ID_WITH_ASSIGNED_FLIGHT);
        userWithAssignedFlight.setPosition(userPosition);

        Optional<Flight> actual = flightService.takeUserFlight(EXISTING_FLIGHT_ID, userWithAssignedFlight);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_FLIGHT_ID, actual.get().getID());
    }

    @Test
    void takeUserFlightByUserWithoutAssignedFlightTest() throws ServiceException {
        Position userPosition = new Position();
        userPosition.setRoleID(USER_POLE_ID);

        User userWithoutAssignedFlight = new User();
        userWithoutAssignedFlight.setID(USER_ID_WITHOUT_ASSIGNED_FLIGHT);
        userWithoutAssignedFlight.setPosition(userPosition);

        Optional<Flight> actual = flightService.takeUserFlight(EXISTING_FLIGHT_ID, userWithoutAssignedFlight);
        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    void takeUserFlightByDispatcherTest() throws ServiceException {
        Position dispatcherPosition = new Position();
        dispatcherPosition.setRoleID(DISPATCHER_POLE_ID);

        User dispatcher = new User();
        dispatcher.setID(DISPATCHER_ID);
        dispatcher.setPosition(dispatcherPosition);

        Optional<Flight> actual = flightService.takeUserFlight(EXISTING_FLIGHT_ID, dispatcher);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_FLIGHT_ID, actual.get().getID());
    }

    @Test
    void takeUserFlightByInvalidFlightIDTest() throws ServiceException {
        Position userPosition = new Position();
        userPosition.setRoleID(USER_POLE_ID);

        User userWithAssignedFlight = new User();
        userWithAssignedFlight.setID(USER_ID_WITH_ASSIGNED_FLIGHT);
        userWithAssignedFlight.setPosition(userPosition);

        Optional<Flight> actual = flightService.takeUserFlight(INVALID_FLIGHT_ID, userWithAssignedFlight);
        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    void takeUserFlightByNullUserTest() throws ServiceException {
        Optional<Flight> actual = flightService.takeUserFlight(EXISTING_FLIGHT_ID, null);
        Assertions.assertFalse(actual.isPresent());
    }
}
