package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.validation.RouteValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RouteDtoValidatorTest {
    private final RouteValidator validator = new RouteDtoValidator();
    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;
    private static final int ABOVE_ZERO_INT = -100;
    private static final int ZERO_INT = 0;
    private static final int GREATER_ZERO_INT = 100;

    @Test
    void validateValidDistanceTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(GREATER_ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertTrue(validator.validateDto(dto));
    }

    @Test
    void validateInvalidZeroDistanceTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertFalse(validator.validateDto(dto));
    }

    @Test
    void validateInvalidAboveZeroDistanceTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(ABOVE_ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertFalse(validator.validateDto(dto));
    }

    @Test
    void validateValidDepartureIDTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(GREATER_ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertTrue(validator.validateDto(dto));
    }

    @Test
    void validateInvalidDepartureIDTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(GREATER_ZERO_INT);
        dto.setDepartureID(INVALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertFalse(validator.validateDto(dto));
    }

    @Test
    void validateValidDestinationIDTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(GREATER_ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(VALID_ID);

        Assertions.assertTrue(validator.validateDto(dto));
    }

    @Test
    void validateInvalidDestinationIDTest() {
        RouteDto dto = new RouteDto();
        dto.setDistance(GREATER_ZERO_INT);
        dto.setDepartureID(VALID_ID);
        dto.setDestinationID(INVALID_ID);

        Assertions.assertFalse(validator.validateDto(dto));
    }
}
