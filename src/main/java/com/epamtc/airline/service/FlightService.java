package com.epamtc.airline.service;

import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.SearchQuery;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    /**
     * Retrieves a {@link List} of {@link Flight} entities that belongs to user.
     * @param user {@link User} entity.
     * @return  {@link List} of {@link Flight} entities to which the user was assigned, or an empty {@link List} if the user was not assigned to flight.
     * @throws ServiceException if retrieving is impossible.
     */
    List<Flight> takeUserFlights(User user) throws ServiceException;

    /**
     * Confirms the user's participation in the flight.
     * @param flightID ID of {@link Flight} entity.
     * @param user {@link User} entity.
     * @return {@code true} if flight is confirmed, {@code false} otherwise.
     * @throws ServiceException if confirming is impossible;
     */
    boolean confirmFlight(long flightID, User user) throws ServiceException;

    /**
     * Retrieves a flight, that belongs user.
     * @param flightID ID of {@link Flight} entity.
     * @param user {@link User} entity.
     * @return An {@link Optional} describing flight entity, or an empty {@link Optional} if flight does not belong to user.
     * @throws ServiceException if retrieving is impossible.
     */
    Optional<Flight> takeUserFlight(long flightID, User user) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<Flight> takeUnassignedFlights() throws ServiceException;

    /**
     *
     * @param flightID
     * @return
     * @throws ServiceException
     */
    Optional<Flight> takeFlight(long flightID) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<Flight> takeAllFlights() throws ServiceException;

    /**
     *
     * @param flightID
     * @param statusID
     * @throws ServiceException
     */
    void changeFlightStatus(long flightID, long statusID) throws ServiceException;

    /**
     *
     * @param flightID
     * @return
     * @throws ServiceException
     */
    boolean cancelFlight(long flightID) throws ServiceException;

    /**
     *
     * @param flightDto
     * @throws ServiceException
     */
    void createFlight(FlightDto flightDto) throws ServiceException;

    /**
     *
     * @param flightDto
     * @return
     * @throws ServiceException
     */
    boolean editFlight(FlightDto flightDto) throws ServiceException;

    /**
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    List<Flight> searchFlights(SearchQuery query) throws ServiceException;

    /**
     *
     * @param statusID
     * @return
     * @throws ServiceException
     */
    FlightStatus takeFlightStatus(long statusID) throws ServiceException;
}