package com.epamtc.airline.service.impl;

import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class FlightServiceImplTest {
    private final FlightService flightService = new FlightServiceImpl();
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

}
