package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.dto.RouteDto;
import com.epamtc.airline.service.validation.RouteValidator;

public class RouteDtoValidator implements RouteValidator {
    @Override
    public boolean validateDto(RouteDto routeDto) {
        return routeDto.getDistance() > 0
                && routeDto.getDepartureID() > 0
                && routeDto.getDestinationID() > 0;
    }

}
