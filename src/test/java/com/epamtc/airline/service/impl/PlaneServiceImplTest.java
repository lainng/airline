package com.epamtc.airline.service.impl;

import com.epamtc.airline.ConnectionPoolExtension;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(ConnectionPoolExtension.class)
public class PlaneServiceImplTest {
    private final PlaneService planeService = new PlaneServiceImpl();
    private static final long EXISTING_PLANE_ID = 19L;
    private static final long INVALID_PLANE_ID = -19L;

    @Test
    void takePlaneByExistingIDTest() throws ServiceException {
        Optional<Plane> actual = planeService.takePlane(EXISTING_PLANE_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_PLANE_ID, actual.get().getID());
    }

    @Test
    void takePlaneByInvalidIDTest() throws ServiceException {
        Optional<Plane> actual = planeService.takePlane(INVALID_PLANE_ID);
        Assertions.assertFalse(actual.isPresent());
    }
}
