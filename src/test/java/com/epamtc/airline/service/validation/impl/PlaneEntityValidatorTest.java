package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.validation.PlaneValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlaneEntityValidatorTest {
    private final PlaneValidator validator = new PlaneEntityValidator();
    private static final String CORRECT_MODEL = "Boeing 777";
    private static final String INCORRECT_MODEL = "-Boeing 777-";
    private static final int ABOVE_ZERO_INT = -100;
    private static final int ZERO_INT = 0;
    private static final int GREATER_ZERO_INT = 100;

    @Test
    void validateValidModelTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertTrue(validator.validate(plane));
    }

    @Test
    void validateInvalidModelTest() {
        Plane plane = new Plane();
        plane.setModel(INCORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }

    @Test
    void validateValidFlightRangeTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertTrue(validator.validate(plane));
    }

    @Test
    void validateInvalidZeroFlightRangeTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }

    @Test
    void validateInvalidAboveZeroFlightRangeTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(ABOVE_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }

    @Test
    void validateValidPassengerCapacityTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertTrue(validator.validate(plane));
    }

    @Test
    void validateInvalidZeroPassengerCapacityTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }

    @Test
    void validateInvalidAboveZeroPassengerCapacityTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(ABOVE_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }

    @Test
    void validateValidFlyingHoursTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(GREATER_ZERO_INT);

        Assertions.assertTrue(validator.validate(plane));
    }

    @Test
    void validateValidZeroFlyingHoursTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(ZERO_INT);

        Assertions.assertTrue(validator.validate(plane));
    }

    @Test
    void validateInvalidAboveZeroFlyingHoursTest() {
        Plane plane = new Plane();
        plane.setModel(CORRECT_MODEL);
        plane.setFlightRange(GREATER_ZERO_INT);
        plane.setPassengerCapacity(GREATER_ZERO_INT);
        plane.setFlyingHours(ABOVE_ZERO_INT);

        Assertions.assertFalse(validator.validate(plane));
    }
}
