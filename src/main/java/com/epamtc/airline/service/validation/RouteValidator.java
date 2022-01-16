package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.dto.RouteDto;

public interface RouteValidator {
    boolean validateDto(RouteDto routeDto);
}
