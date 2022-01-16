package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.City;

public interface CityValidator {
    boolean validateName(City city);
}
