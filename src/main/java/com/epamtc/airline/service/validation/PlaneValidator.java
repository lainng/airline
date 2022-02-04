package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.Plane;

public interface PlaneValidator {
    /**
     * Validates plane's entity.
     * @param plane An {@link Plane} entity.
     * @return {@code true} if plane's entity is valid otherwise returns {@code false}.
     */
    boolean validate(Plane plane);
}