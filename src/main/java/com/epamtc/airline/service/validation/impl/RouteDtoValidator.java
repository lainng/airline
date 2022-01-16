package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.validation.RouteValidator;

public class RouteDtoValidator implements RouteValidator {
    @Override
    public boolean validateDto(RouteDto routeDto) {
        return isGreaterZero(routeDto.getDistance());
    }

    private boolean isGreaterZero(int value) {
        return value > 0;
    }
}
