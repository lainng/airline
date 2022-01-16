package com.epamtc.airline.service.validation;

import com.epamtc.airline.service.validation.impl.CityEntityValidator;
import com.epamtc.airline.service.validation.impl.PlaneEntityValidator;
import com.epamtc.airline.service.validation.impl.RouteDtoValidator;
import com.epamtc.airline.service.validation.impl.UserFormDataValidator;

public class ValidatorFactory {
    private final UserValidator userValidator = new UserFormDataValidator();
    private final PlaneValidator planeValidator = new PlaneEntityValidator();
    private final CityValidator cityValidator = new CityEntityValidator();
    private final RouteValidator routeDtoValidator = new RouteDtoValidator();

    private ValidatorFactory() {}

    public UserValidator getUserValidator() {
        return userValidator;
    }
    public PlaneValidator getPlaneValidator() {
        return planeValidator;
    }
    public CityValidator getCityValidator() {
        return cityValidator;
    }
    public RouteValidator getRouteDtoValidator() {
        return routeDtoValidator;
    }

    public static ValidatorFactory getInstance() {
        return ValidatorFactoryHolder.INSTANCE;
    }

    private static class ValidatorFactoryHolder {
        static final ValidatorFactory INSTANCE = new ValidatorFactory();
    }
}
