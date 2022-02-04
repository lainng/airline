package com.epamtc.airline.service.impl;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CrewServiceImplTest {
    private final CrewService crewService = new CrewServiceImpl();
    private static final long EXISTING_CREW_ID = 37L;
    private static final long INVALID_CREW_ID = -37L;
    private static final long EXISTING_FLIGHT_ID = 24L;
    private static final long INVALID_FLIGHT_ID = -24L;

    @Test
    void takeCrewByExistingIDTest() throws ServiceException {
        Optional<Crew> actual = crewService.takeCrewByID(EXISTING_CREW_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_CREW_ID, actual.get().getID());
    }

    @Test
    void takeCrewByInvalidIDTest() throws ServiceException {
        Optional<Crew> actual = crewService.takeCrewByID(INVALID_CREW_ID);
        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    void takeCrewByExistingFlightIDTest() throws ServiceException {
        Optional<Crew> actual = crewService.takeCrewByFlightID(EXISTING_FLIGHT_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_FLIGHT_ID, actual.get().getAssignedFlight().getID());
    }

    @Test
    void takeCrewByInvalidFlightIDTest() throws ServiceException {
        Optional<Crew> actual = crewService.takeCrewByFlightID(INVALID_FLIGHT_ID);
        Assertions.assertFalse(actual.isPresent());
    }
}
