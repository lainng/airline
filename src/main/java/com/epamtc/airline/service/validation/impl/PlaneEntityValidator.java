package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.validation.PlaneValidator;

public class PlaneEntityValidator implements PlaneValidator {
    @Override
    public boolean validatePlaneEntity(Plane plane) {
        return !plane.getModel().isEmpty()
                && plane.getFlyingHours() >= 0
                && isGreaterZero(plane.getPassengerCapacity())
                && isGreaterZero(plane.getFlightRange());
    }

    private boolean isGreaterZero(int value) {
        return value > 0;
    }
}