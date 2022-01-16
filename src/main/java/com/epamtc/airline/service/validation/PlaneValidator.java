package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.Plane;

public interface PlaneValidator {
    boolean validatePlaneEntity(Plane plane);
}
