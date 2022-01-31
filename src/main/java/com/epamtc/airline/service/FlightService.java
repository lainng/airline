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
     * @return {@link List} of {@link Flight} entities to which the user was assigned, or an empty {@link List} if the user was not assigned to the flight.
     * @throws ServiceException if retrieving is impossible.
     */
    List<Flight> takeUserFlights(User user) throws ServiceException;

    /**
     * Confirms the user's participation in the flight.
     * @param flightID The ID of {@link Flight} entity.
     * @param user {@link User} entity.
     * @return {@code true} if flight is confirmed, {@code false} otherwise.
     * @throws ServiceException if confirming is impossible;
     */
    boolean confirmFlight(long flightID, User user) throws ServiceException;

    /**
     * Retrieves a flight, that belongs user.
     * @param flightID ID of {@link Flight} entity.
     * @param user {@link User} entity.
     * @return A {@link Optional} describing flight entity, or an empty {@link Optional} if flight does not belong to user.
     * @throws ServiceException if retrieving is impossible.
     */
    Optional<Flight> takeUserFlight(long flightID, User user) throws ServiceException;

    /**
     * Retrieves a list of flights for which crews are not assigned.
     * @return {@link List} of {@link Flight} entities for which crews are not assigned, or an empty {@link List} if the flights is not found.
     * @throws ServiceException if retrieving of the list of flights is impossible.
     */
    List<Flight> takeUnassignedFlights() throws ServiceException;

    /**
     * Retrieves a flight by specified ID.
     * @param flightID The ID of {@link Flight} entity.
     * @return A {@link Optional} describing flight entity, or an empty {@link Optional} if the flight is not found.
     * @throws ServiceException if retrieving of the flight is impossible.
     */
    Optional<Flight> takeFlight(long flightID) throws ServiceException;

    /**
     * Retrieves all flights.
     * @return {@link List} of {@link Flight} entities or an empty {@link List} if the flights is not found.
     * @throws ServiceException if retrieving of the list of flights is impossible.
     */
    List<Flight> takeAllFlights() throws ServiceException;

    /**
     * Changes the flight status in the flight.
     * @param flightID The ID of the flight whose status is being changed.
     * @param statusID The ID of the flight status.
     * @throws ServiceException if changing of the status is impossible.
     */
    void changeFlightStatus(long flightID, long statusID) throws ServiceException;

    /**
     * Canceled the flight.
     * @param flightID The ID of the flight whose is being canceled.
     * @return {@code true} if the entity canceled or {@code false} if the flight is not found in the data source by specified ID.
     * @throws ServiceException if cancelling a flight is impossible.
     */
    boolean cancelFlight(long flightID) throws ServiceException;

    /**
     * Creates a flight in the data source.
     * @param flightDto A {@link FlightDto} entity that contains raw information about the flight entity.
     * @return The ID of a new flight entity.
     * @throws ServiceException if creating a flight's entity is impossible.
     */
    long createFlight(FlightDto flightDto) throws ServiceException;

    /**
     * Edited flight's entity from a data source.
     * @param flightDto A {@link FlightDto} entity that contains raw information about the flight entity.
     * @throws ServiceException if editing a flight's entity is impossible.
     */
    void editFlight(FlightDto flightDto) throws ServiceException;

    /**
     * Retrieves the list of flights by the specified search query.
     * @param query A {@link SearchQuery} entity.
     * @return {@link List} of {@link Flight} entities or an empty {@link List} if the flights is not found.
     * @throws ServiceException if searching is impossible.
     */
    List<Flight> searchFlights(SearchQuery query) throws ServiceException;

    /**
     * Retrieves the flight status by the specified ID.
     * @param statusID The ID of the status.
     * @return A {@link FlightStatus} entity that linked with the data source by specified ID.
     * @throws ServiceException if retrieving is impossible.
     */
    FlightStatus takeFlightStatus(long statusID) throws ServiceException;
}