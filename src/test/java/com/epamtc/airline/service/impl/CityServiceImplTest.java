package com.epamtc.airline.service.impl;

import com.epamtc.airline.ConnectionPoolExtension;
import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.CityService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(ConnectionPoolExtension.class)
public class CityServiceImplTest {
    private final CityService cityService = new CityServiceImpl();
    private static final long EXISTING_CITY_ID = 11L;
    private static final long INVALID_CITY_ID = -11L;

    @Test
    void takePlaneByExistingIDTest() throws ServiceException {
        Optional<City> actual = cityService.takeCity(EXISTING_CITY_ID);
        if (!actual.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_CITY_ID, actual.get().getID());
    }

    @Test
    void takePlaneByInvalidIDTest() throws ServiceException {
        Optional<City> actual = cityService.takeCity(INVALID_CITY_ID);
        Assertions.assertFalse(actual.isPresent());
    }
}
