package com.epamtc.airline.service;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CrewService {

    /**
     *
     * @param flightID
     * @return
     * @throws ServiceException
     */
    Optional<Crew> takeCrewByFlightID(long flightID) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<Crew> takeAllCrews() throws ServiceException;

    /**
     *
     * @param crewCreationDto
     * @throws ServiceException
     */
    void updateCrew(CrewCreationDto crewCreationDto) throws ServiceException;

    /**
     *
     * @param crewCreationDto
     * @throws ServiceException
     */
    void createCrew(CrewCreationDto crewCreationDto) throws ServiceException;

    /**
     *
     * @param crewID
     * @return
     * @throws ServiceException
     */
    boolean deleteCrew(long crewID) throws ServiceException;

    /**
     *
     * @param crewID
     * @return
     * @throws ServiceException
     */
    Optional<Crew> takeCrewByID(long crewID) throws ServiceException;

    /**
     *
     * @param userID
     * @return
     * @throws ServiceException
     */
    List<Crew> getUserCrews(long userID) throws ServiceException;
}