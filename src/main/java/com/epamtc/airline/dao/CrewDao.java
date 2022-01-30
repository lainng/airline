package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.entity.dto.CrewDto;

import java.util.List;

public interface CrewDao {

    /**
     * Fetches a crew by the flight ID.
     * @param flightID The ID of the flight.
     * @return An {@link CrewDto} instance or {@code null} if there are no crew by specified flight ID.
     * @throws DaoException if a data source access error or other errors.
     */
    CrewDto findCrewByFlightID(long flightID) throws DaoException;

    /**
     * Fetches a crew by its ID.
     * @param crewID The ID of the crew.
     * @return An {@link CrewDto} instance or {@code null} if there are no crew by specified ID.
     * @throws DaoException if a data source access error or other errors.
     */
    CrewDto findCrewByID(long crewID) throws DaoException;

    /**
     * Fetches a list of all crews.
     * @return The {@link List} of {@link CrewDto} entities, or an empty {@link List} if there are no crews.
     * @throws DaoException if a data source access error or other errors.
     */
    List<CrewDto> findAllCrews() throws DaoException;

    /**
     * Updates the crew information.
     * @param crewCreationDto An {@link CrewCreationDto} instance that contains updated information.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateCrew(CrewCreationDto crewCreationDto) throws DaoException;

    /**
     * Adds crew to the data source.
     * @param crewCreationDto An {@link CrewCreationDto} instance.
     * @throws DaoException if a data source access error or other errors.
     */
    void addCrew(CrewCreationDto crewCreationDto) throws DaoException;

    /**
     * Deleted the crew.
     * @param crewID The ID of the crew to be removed.
     * @throws DaoException if a data source access error or other errors.
     */
    void deleteCrew(long crewID) throws DaoException;
}