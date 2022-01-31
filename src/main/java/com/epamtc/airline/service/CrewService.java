package com.epamtc.airline.service;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CrewService {

    /**
     * Retrieves a crew's entity by a specified flight ID.
     * @param flightID The ID of the flight.
     * @return A {@link Optional} describing crew entity, or an empty {@link Optional} if the crew is not found.
     * @throws ServiceException if retrieving of the crew is impossible.
     */
    Optional<Crew> takeCrewByFlightID(long flightID) throws ServiceException;

    /**
     * Retrieves all crews.
     * @return The {@link List} of crews or the empty {@link List} if crews is not found.
     * @throws ServiceException if retrieving of the crew's list is impossible.
     */
    List<Crew> takeAllCrews() throws ServiceException;

    /**
     * Edited crew's entity from a data source.
     * @param crewCreationDto A {@link CrewCreationDto} entity that contains raw information about the entity.
     * @throws ServiceException if editing a crew's entity is impossible or there are no edited crew in the data source.
     */
    void editCrew(CrewCreationDto crewCreationDto) throws ServiceException;

    /**
     * Creates crew's entity from the data source.
     * @param crewCreationDto A {@link CrewCreationDto} entity that contains raw information about the crew entity.
     * @return {@code true} if the entity created or {@code false} if the chosen flight is assigned to the other crew.
     * @throws ServiceException if creating a crew's entity is impossible.
     */
    boolean createCrew(CrewCreationDto crewCreationDto) throws ServiceException;

    /**
     * Deletes the crew that is linked by specified ID from the data source.
     * @param crewID The ID of the crew.
     * @return {@code true} if the entity deleted or {@code false} if the crew is not found in the data source by specified ID.
     * @throws ServiceException if deleting a crew's entity is impossible.
     */
    boolean deleteCrew(long crewID) throws ServiceException;

    /**
     * Retrieves a crew's entity by a specified ID.
     * @param crewID The ID of the crew.
     * @return A {@link Optional} describing crew entity, or an empty {@link Optional} if the crew is not found.
     * @throws ServiceException if retrieving of the crew is impossible.
     */
    Optional<Crew> takeCrewByID(long crewID) throws ServiceException;

    /**
     * Retrieves the crews the user was assigned to.
     * @param userID The ID of the user.
     * @return The {@link List} of crews or the empty {@link List} if crews is not found.
     * @throws ServiceException if retrieving of the crew's list is impossible.
     */
    List<Crew> takeUserCrews(long userID) throws ServiceException;
}