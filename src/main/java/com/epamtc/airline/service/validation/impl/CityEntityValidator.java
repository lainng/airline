package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.validation.CityValidator;

import java.util.regex.Pattern;

public class CityEntityValidator implements CityValidator {
    private static final String NAME_REGEXP = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$";

    @Override
    public boolean validate(City city) {
        Pattern pattern = Pattern.compile(NAME_REGEXP);
        return pattern.matcher(city.getName()).matches();
    }
}
