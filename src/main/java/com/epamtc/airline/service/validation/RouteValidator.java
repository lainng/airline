package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.dto.RouteDto;

public interface RouteValidator {
    /**
     * Validates route DTO.
     * @param routeDto An {@link RouteDto} entity.
     * @return {@code true} if route DTO entity is valid otherwise returns {@code false}.
     */
    boolean validateDto(RouteDto routeDto);
}