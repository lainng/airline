package com.epamtc.airline.service.impl;

import com.epamtc.airline.ConnectionPoolExtension;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(ConnectionPoolExtension.class)
public class RouteServiceImplTest {
    private final RouteService routeService = new RouteServiceImpl();
    private static final long EXISTING_ROUTE_ID = 34L;
    private static final long INVALID_ROUTE_ID = -34L;

    /*@AfterAll
    static void tearDown() {
        ConnectionPool.getInstance().terminate();
    }*/

    @Test
    void takePlaneByExistingIDTest() throws ServiceException {
        Optional<Route> actual = routeService.takeRoute(EXISTING_ROUTE_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_ROUTE_ID, actual.get().getID());
    }

    @Test
    void takePlaneByInvalidIDTest() throws ServiceException {
        Optional<Route> actual = routeService.takeRoute(INVALID_ROUTE_ID);
        Assertions.assertFalse(actual.isPresent());
    }
}
