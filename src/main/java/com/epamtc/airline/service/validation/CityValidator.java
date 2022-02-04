package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.City;

public interface CityValidator {

    /**
     * Validates city entity.
     * @param city An {@link City} entity.
     * @return {@code true} if city entity is valid otherwise returns {@code false}.
     */
    boolean validate(City city);
}