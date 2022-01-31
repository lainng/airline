package com.epamtc.airline.service;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.exception.ServiceException;

public interface MailService {

    /**
     * Sends a mail message to all dispatchers about creating a new flight.
     * @param flightID The ID of the new flight.
     * @param locale The language code of mail content.
     * @throws ServiceException if sending is not impossible.
     */
    void sendNewFlightMail(long flightID, String locale) throws ServiceException;

    /**
     * Sends a mail message to all crew members about assigning to the new crew.
     * @param flightID The ID of the flight that crew assigned to.
     * @param locale The language code of mail content.
     * @throws ServiceException if sending is not impossible.
     */
    void sendNewCrewMail(long flightID, String locale) throws ServiceException;

    /**
     * Sends a mail message to all crew members about deleting the crew.
     * @param crew A {@link Crew} entity that will be deleted.
     * @param locale The language code of mail content.
     * @throws ServiceException if sending is not impossible.
     */
    void sendDeleteCrewMail(Crew crew, String locale) throws ServiceException;

    /**
     * Sends messages to crew members about removal from the crew, change of flight and assignment to this crew.
     * @param dto A {@link CrewCreationDto} that contains information about new crew members and the assigned flight.
     * @param locale The language code of mail content.
     * @throws ServiceException if sending is not impossible.
     */
    void sendEditCrewMail(CrewCreationDto dto, String locale) throws ServiceException;

    /**
     * Sends a mail message to all dispatchers about cancelling the flight.
     * @param flightID The ID of the flight that will be canceled.
     * @param locale The language code of mail content.
     * @throws ServiceException if sending is not impossible.
     */
    void sendCancelFlightMail(long flightID, String locale) throws ServiceException;

}