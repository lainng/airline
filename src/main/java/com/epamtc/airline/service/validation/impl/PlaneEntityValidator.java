package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.validation.PlaneValidator;

import java.util.regex.Pattern;

public class PlaneEntityValidator implements PlaneValidator {
    private static final String MODEL_REGEXP = "^[A-Za-zА-Яа-я0-9]+(?:[ -][A-Za-zА-Яа-я0-9]+)*$";

    @Override
    public boolean validate(Plane plane) {
        Pattern pattern = Pattern.compile(MODEL_REGEXP);
        return pattern.matcher(plane.getModel()).matches()
                && plane.getFlyingHours() >= 0
                && isGreaterZero(plane.getPassengerCapacity())
                && isGreaterZero(plane.getFlightRange());
    }

    private boolean isGreaterZero(int value) {
        return value > 0;
    }
}