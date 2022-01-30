package com.epamtc.airline.service.impl;

import com.epamtc.airline.command.UserRole;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.service.*;
import com.epamtc.airline.service.exception.ServiceException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MAIL_CONTEXT_BUNDLE = "mailContent";
    private static final String MAIL_CONFIG_PROPERTIES_PATH = "mail.properties";
    private static final String MESSAGE_CONTENT_TYPE = "text/plain; charset=UTF-8";
    private static final String SUBJECT_ENCODING = "UTF-8";
    private static final String DATETIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private static final String COLON = ": ";
    private static final String WHITESPACE = " ";
    private static final String NEW_FLIGHT_SUBJECT = "newFlight.subject";
    private static final String NEW_FLIGHT_CONTENT = "newFlight.content";
    private static final String NEW_CREW_SUBJECT = "newCrew.subject";
    private static final String NEW_CREW_CONTENT = "newCrew.content";
    private static final String DELETE_CREW_SUBJECT = "deleteCrew.subject";
    private static final String DELETE_CREW_CONTENT = "deleteCrew.content";
    private static final String CHANGE_CREW_SUBJECT = "changeCrew.subject";
    private static final String DELETE_EMPL_FROM_CREW_CONTENT = "deleteEmployeeFromCrew.content";
    private static final String NEW_EMPL_IN_CREW_CONTENT = "newEmplInCrew.content";
    private static final String CANCEL_FLIGHT_SUBJECT = "cancelFlight.subject";
    private static final String CANCEL_FLIGHT_CONTENT = "cancelFlight.content";

    @Override
    public void sendNewFlightMail(long flightID, String locale) throws ServiceException {
        MimeMessage mimeMessage = createMessage(loadMailSettingsProperties());
        Flight newFlight = takeFlight(flightID);

        ResourceBundle mailContentBundle = takeMailContentBundle(locale);
        String subject = mailContentBundle.getString(NEW_FLIGHT_SUBJECT);
        subject = String.format(subject, newFlight.getID());
        String content = mailContentBundle.getString(NEW_FLIGHT_CONTENT);
        content = formatNewFlightMailContent(newFlight, content);

        List<User> dispatchers = takeDispatchers();
        for (User dispatcher : dispatchers) {
            sendMessage(
                    mimeMessage,
                    subject,
                    content,
                    dispatcher.getEmail()
            );
        }
    }

    @Override
    public void sendNewCrewMail(long flightID, String locale) throws ServiceException {
        MimeMessage mimeMessage = createMessage(loadMailSettingsProperties());

        Crew crew = takeCrewByFlightID(flightID);
        ResourceBundle mailContentBundle = takeMailContentBundle(locale);
        String subject = mailContentBundle.getString(NEW_CREW_SUBJECT);
        subject = String.format(subject, crew.getID());
        String content = mailContentBundle.getString(NEW_CREW_CONTENT);
        content = formatNewCrewMailContent(crew, content);

        for (User employee : crew.getMembers()) {
            sendMessage(
                    mimeMessage,
                    subject,
                    content,
                    employee.getEmail()
            );
        }
    }

    @Override
    public void sendDeleteCrewMail(Crew crew, String locale) throws ServiceException {
        MimeMessage mimeMessage = createMessage(loadMailSettingsProperties());

        ResourceBundle mailContentBundle = takeMailContentBundle(locale);
        String subject = mailContentBundle.getString(DELETE_CREW_SUBJECT);
        subject = String.format(subject, crew.getID());
        String content = mailContentBundle.getString(DELETE_CREW_CONTENT);
        content = formatDeleteCrewMailContent(crew, content);

        for (User employee : crew.getMembers()) {
            sendMessage(
                    mimeMessage,
                    subject,
                    content,
                    employee.getEmail()
            );
        }
    }

    @Override
    public void sendEditCrewMail(CrewCreationDto dto, String locale) throws ServiceException {
        MimeMessage mimeMessage = createMessage(loadMailSettingsProperties());
        ResourceBundle mailContentBundle = takeMailContentBundle(locale);
        Crew oldCrew = takeCrewByID(dto.getID());
        Flight assignedFlight = takeFlight(dto.getAssignedFlightID());
        List<User> oldMembers = takeDeletedMembers(oldCrew, dto);
        List<User> newMembers = takeNewCrewMembers(oldCrew, dto);
        List<User> retainedMembers = takeRetainedMembers(oldCrew, dto);

        String subject = mailContentBundle.getString(CHANGE_CREW_SUBJECT);
        subject = String.format(subject, dto.getID());
        String deletedMembersContent = mailContentBundle.getString(DELETE_EMPL_FROM_CREW_CONTENT);
        deletedMembersContent = String.format(deletedMembersContent, dto.getID());
        String newMembersContent = mailContentBundle.getString(NEW_EMPL_IN_CREW_CONTENT);
        newMembersContent = String.format(newMembersContent, dto.getID());

        for (User employee : oldMembers) {
            sendMessage(
                    mimeMessage,
                    subject,
                    deletedMembersContent,
                    employee.getEmail()
            );
        }

        for (User employee : newMembers) {
            sendMessage(
                    mimeMessage,
                    subject,
                    newMembersContent,
                    employee.getEmail()
            );
        }

        if (oldCrew.getAssignedFlight().getID() != assignedFlight.getID()) {
            String content = mailContentBundle.getString(NEW_FLIGHT_CONTENT);
            content = formatNewFlightMailContent(assignedFlight, content);
            for (User employee : retainedMembers) {
                sendMessage(
                        mimeMessage,
                        subject,
                        content,
                        employee.getEmail()
                );
            }
        }
    }

    @Override
    public void sendCancelFlightMail(long flightID, String locale) throws ServiceException {
        MimeMessage mimeMessage = createMessage(loadMailSettingsProperties());
        ResourceBundle mailContentBundle = takeMailContentBundle(locale);
        String subject = mailContentBundle.getString(CANCEL_FLIGHT_SUBJECT);
        subject = String.format(subject, flightID);
        String content = mailContentBundle.getString(CANCEL_FLIGHT_CONTENT);
        content = String.format(content, flightID);

        List<User> dispatchers = takeDispatchers();
        for (User dispatcher : dispatchers) {
            sendMessage(
                    mimeMessage,
                    subject,
                    content,
                    dispatcher.getEmail()
            );
        }
    }

    private Properties loadMailSettingsProperties() throws ServiceException {
        Properties mailProperties = new Properties();
        InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(MAIL_CONFIG_PROPERTIES_PATH);
        if (fileInputStream == null) {
            LOGGER.error("File {} doesn't exist", MAIL_CONFIG_PROPERTIES_PATH);
            throw new ServiceException("File " + MAIL_CONFIG_PROPERTIES_PATH + " doesn't exist");
        }

        try {
            mailProperties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("Unable to read mail properties. {}", e.getMessage());
            throw new ServiceException("Unable to read mail properties.", e);
        }
        return mailProperties;
    }

    private MimeMessage createMessage(Properties mailProperties) throws ServiceException {
        MailSessionCreator sessionCreator = new MailSessionCreator(mailProperties);
        Session session = sessionCreator.createSession();
        return new MimeMessage(session);
    }

    private Flight takeFlight(long flightID) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        Optional<Flight> flight = flightService.takeFlight(flightID);
        if (!flight.isPresent()) {
            LOGGER.error("Unable to take the flight.");
            throw new ServiceException("Unable to take the flight.");
        }
        return flight.get();
    }

    private Crew takeCrewByFlightID(long flightID) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        Optional<Crew> crew = crewService.takeCrewByFlightID(flightID);
        if (!crew.isPresent()) {
            LOGGER.error("Unable to take the crew.");
            throw new ServiceException("Unable to take the crew.");
        }
        return crew.get();
    }

    private Crew takeCrewByID(long crewID) throws ServiceException {
        CrewService crewService = ServiceFactory.getInstance().getCrewService();
        Optional<Crew> crew = crewService.takeCrewByID(crewID);
        if (!crew.isPresent()) {
            LOGGER.error("Unable to take the crew.");
            throw new ServiceException("Unable to take the crew.");
        }
        return crew.get();
    }

    private List<User> takeDispatchers() throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users = userService.takeAllUsers();
        users.removeIf(user -> user.getPosition().getRoleID() != UserRole.DISPATCHER);
        return users;
    }

    private List<User> takeNewCrewMembers(Crew oldCrew, CrewCreationDto newCrewDto) throws ServiceException {
        List<User> newCrewMembers = takeUsersListFromDto(newCrewDto);
        newCrewMembers.removeAll(oldCrew.getMembers());
        return newCrewMembers;
    }

    private List<User> takeDeletedMembers(Crew oldCrew, CrewCreationDto newCrewDto) throws ServiceException {
        List<User> newCrewMembers = takeUsersListFromDto(newCrewDto);
        List<User> deletedMembers = new ArrayList<>(oldCrew.getMembers().size());
        deletedMembers.addAll(oldCrew.getMembers());
        deletedMembers.removeAll(newCrewMembers);
        return deletedMembers;
    }

    private List<User> takeRetainedMembers(Crew oldCrew, CrewCreationDto newCrewDto) throws ServiceException {
        List<User> newCrewMembers = takeUsersListFromDto(newCrewDto);
        List<User> retainedMembers = new ArrayList<>(oldCrew.getMembers().size());
        retainedMembers.addAll(oldCrew.getMembers());
        retainedMembers.retainAll(newCrewMembers);
        return retainedMembers;
    }

    private List<User> takeUsersListFromDto(CrewCreationDto dto) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users = new ArrayList<>(dto.getMembers().length);
        for (long id : dto.getMembers()) {
            Optional<User> optionalUser = userService.takeUser(id);
            optionalUser.ifPresent(users::add);
        }
        return users;
    }

    private String formatNewFlightMailContent(Flight flight, String content) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        String formattedDatetime = formatter.format(flight.getDepartureTime());
        String planeModel = flight.getPlane().getModel();
        String departure = flight.getRoute().getDeparture().getName();
        String destination = flight.getRoute().getDestination().getName();
        return String.format(
                content,
                flight.getID(),
                formattedDatetime,
                planeModel,
                departure,
                destination
        );
    }

    private String formatNewCrewMailContent(Crew crew, String content) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        String formattedDatetime = formatter.format(crew.getAssignedFlight().getDepartureTime());
        content = String.format(
                content,
                crew.getID(),
                crew.getAssignedFlight().getID(),
                formattedDatetime
        );
        StringBuilder builder = new StringBuilder(content);
        for (User employee : crew.getMembers()) {
            builder.append("\n")
                    .append(employee.getPosition().getName()).append(COLON)
                    .append(employee.getFirstName()).append(WHITESPACE).append(employee.getLastName());
        }
        return builder.toString();
    }

    private String formatDeleteCrewMailContent(Crew crew, String content) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN);
        String formattedDatetime = formatter.format(crew.getAssignedFlight().getDepartureTime());
        return String.format(
                content,
                crew.getID(),
                formattedDatetime
        );
    }

    private ResourceBundle takeMailContentBundle(String locale) {
        return ResourceBundle.getBundle(MAIL_CONTEXT_BUNDLE, new Locale(locale));
    }

    private void sendMessage(MimeMessage mimeMessage,
                             String subject,
                             String content,
                             String recipientEmail) throws ServiceException {
        try {
            mimeMessage.setSubject(subject, SUBJECT_ENCODING);
            mimeMessage.setContent(content, MESSAGE_CONTENT_TYPE);
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Unable to send message. {}", e.getMessage());
            throw new ServiceException("Unable to send message.", e);
        }
    }
}