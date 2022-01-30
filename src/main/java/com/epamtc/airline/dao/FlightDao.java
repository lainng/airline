package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.SearchQuery;

import java.util.List;

public interface FlightDao {
    /**
     * Fetches the {@link List} of {@link Flight} entities to which the user was assigned.
     * @param userID ID of the {@link User} entity.
     * @return {@link List} of {@link FlightDto} entities to which the user was assigned, or an empty {@link List} if the user was not assigned to the flight.
     * @throws DaoException if a data source access error or other errors.
     */
    List<FlightDto> findUserFlights(long userID) throws DaoException;

    /**
     * Confirms the user's participation in the flight by the flight ID.
     * @param flightID ID of the {@link Flight} entity.
     * @param userID ID of the {@link User} entity.
     * @throws DaoException if a data source access error or other errors.
     */
    void confirmFlightByID(long flightID, long userID) throws DaoException;

    /**
     * Fetches the flight by its ID.
     * @param flightID ID of the {@link Flight} entity.
     * @return An {@link FlightDto} entity or {@code null} if the flight was not found by specified ID.
     * @throws DaoException if a data source access error or other errors.
     */
    FlightDto findFlightByID(long flightID) throws DaoException;

    /**
     * Fetches all flights without crews.
     * @return The {@link List} of {@link FlightDto} entities, or an empty {@link List} if there are no scheduled flights.
     * @throws DaoException if a data source access error or other errors.
     */
    List<FlightDto> findUnassignedFlights() throws DaoException;

    /**
     * Fetches all flights.
     * @return The {@link List} of {@link FlightDto} entities, or an empty {@link List} if there are no flights.
     * @throws DaoException if a data source access error or other errors.
     */
    List<FlightDto> findAllFlights() throws DaoException;

    /**
     * Updated flight status.
     * @param flightID The ID of flight that flight status will be updated.
     * @param statusID The ID of flight status.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateFlightStatus(long flightID, long statusID) throws DaoException;

    /**
     * Canceled flight.
     * @param flightID The ID of flight that will be canceled.
     * @throws DaoException if a data source access error or other errors.
     */
    void cancelFlightByID(long flightID) throws DaoException;

    /**
     * Adds flight entity to the data source.
     * @param flightDto An {@link FlightDto} entity.
     * @return The ID of added flight.
     * @throws DaoException if a data source access error or other errors.
     */
    int addFlight(FlightDto flightDto) throws DaoException;

    /**
     * Updated flight information.
     * @param flightDto An {@link FlightDto} entity.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateFlight(FlightDto flightDto) throws DaoException;

    /**
     * Fetches list of flights that matches the specified search query.
     * @param query An {@link SearchQuery} entity.
     * @return A {@link List} of {@link FlightDto} entities or an empty {@link List} if flights was not found.
     * @throws DaoException if a data source access error or other errors.
     */
    List<FlightDto> findFlightsBySearchQuery(SearchQuery query) throws DaoException;
}