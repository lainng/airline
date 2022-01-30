package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.City;

public interface CityValidator {
    /**
     * Validates city name.
     * @param city An {@link City} entity.
     * @return {@code true} if city name is valid otherwise returns {@code false}.
     */
    boolean validateName(City city);
}