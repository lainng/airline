package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.Plane;

public interface PlaneValidator {
    /**
     * Validates plane entity.
     * @param plane An {@link Plane} entity.
     * @return {@code true} if plane entity is valid otherwise returns {@code false}.
     */
    boolean validatePlaneEntity(Plane plane);
}