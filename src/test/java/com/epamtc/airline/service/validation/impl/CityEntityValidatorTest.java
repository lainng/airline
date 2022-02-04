package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.City;
import com.epamtc.airline.service.validation.CityValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityEntityValidatorTest {
    private final CityValidator cityValidator = new CityEntityValidator();
    private static final String CORRECT_LATIN_NAME = "Buenos Aires";
    private static final String INCORRECT_LATIN_NAME = "Buenos_Aires";
    private static final String CORRECT_CYRILLIC_NAME = "Буэнос-Айрес";
    private static final String INCORRECT_CYRILLIC_NAME = "Буэнос_Айрес";

    @Test
    void validateValidLatinNameTest() {
        City city = new City();
        city.setName(CORRECT_LATIN_NAME);
        Assertions.assertTrue(cityValidator.validate(city));
    }

    @Test
    void validateInvalidLatinNameTest() {
        City city = new City();
        city.setName(INCORRECT_LATIN_NAME);
        Assertions.assertFalse(cityValidator.validate(city));
    }

    @Test
    void validateValidCyrillicNameTest() {
        City city = new City();
        city.setName(CORRECT_CYRILLIC_NAME);
        Assertions.assertTrue(cityValidator.validate(city));
    }

    @Test
    void validateInvalidCyrillicNameTest() {
        City city = new City();
        city.setName(INCORRECT_CYRILLIC_NAME);
        Assertions.assertFalse(cityValidator.validate(city));
    }

}
