package com.epamtc.airline.service;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.exception.ServiceException;

public interface MailService {

    void sendNewFlightMail(long flightID, String locale) throws ServiceException;

    void sendNewCrewMail(long flightID, String locale) throws ServiceException;

    void sendDeleteCrewMail(Crew crew, String locale) throws ServiceException;

    void sendEditCrewMail(CrewCreationDto dto, String locale) throws ServiceException;

}