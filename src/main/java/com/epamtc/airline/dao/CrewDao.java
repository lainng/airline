package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.entity.dto.CrewDto;

import java.util.List;

public interface CrewDao {

    /**
     * Fetches {@link List} of {@link User} entities that entries to crew.
     * @param flightID the ID of {@link Flight} instance.
     * @return {@link List} of {@link User} entities that entries to crew, or empty {@link List} if the crew not found.
     * @throws DaoException if a data source access error or other errors.
     */
    CrewDto findCrewByFlightID(long flightID) throws DaoException;
    CrewDto findCrewByID(long crewID) throws DaoException;
    List<CrewDto> findAllCrews() throws DaoException;
    void updateCrew(CrewCreationDto crewCreationDto) throws DaoException;
    void addCrew(CrewCreationDto crewCreationDto) throws DaoException;
    int deleteCrew(long crewID) throws DaoException;

}
