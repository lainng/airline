package com.epamtc.airline.service.impl;

import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.MailService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MailServiceImplTest {
    private final MailService mailService = new MailServiceImpl();

    @Test
    void sendNewFlightMailTest() throws ServiceException {
        mailService.sendNewFlightMail(24L, "ru");
        Assertions.assertTrue(true);
    }

    @Test
    void sendNewCrewMailTest() throws ServiceException {
        mailService.sendNewCrewMail(24L, "ru");
        Assertions.assertTrue(true);
    }

    @Test
    void sendDeleteCrewMailTest() throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        Optional<Crew> testCrew = crewService.takeCrewByID(37L);
        if (!testCrew.isPresent()) {
            Assertions.fail();
        }
        mailService.sendDeleteCrewMail(testCrew.get(),"ru");
        Assertions.assertTrue(true);
    }

    @Test
    void sendEditCrewMailTest() throws ServiceException {
        CrewCreationDto dtoTest = new CrewCreationDto();
        dtoTest.setID(37L);
        dtoTest.setAssignedFlightID(25L);
        dtoTest.setMembers(new long[] {60L, 61L});

        mailService.sendEditCrewMail(dtoTest, "ru");
        Assertions.assertTrue(true);
    }

    @Test
    void sendCancelFlightMailTest() throws ServiceException {
        mailService.sendCancelFlightMail(24L, "ru");
        Assertions.assertTrue(true);
    }
}
