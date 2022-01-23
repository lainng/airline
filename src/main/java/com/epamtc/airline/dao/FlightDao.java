package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface FlightDao {
    /**
     * Fetches {@link List} of {@link Flight} entities to which the user was assigned.
     * @param userID ID of {@link User} entity.
     * @return {@link List} of {@link Flight} entities to which the user was assigned, or an empty {@link List} if the user was not assigned to flight.
     * @throws DaoException if a data source access error or other errors.
     */
    List<FlightDto> findUserFlights(long userID) throws DaoException;

    /**
     * Confirms the user's participation in the flight by flight ID.
     * @param flightID ID of {@link Flight} entity.
     * @param userID ID of {@link User} entity.
     * @throws DaoException if a data source access error or other errors.
     */
    void confirmFlightByID(long flightID, long userID) throws DaoException;

    /**
     * Fetches flight by its ID.
     * @param flightID ID of {@link Flight} entity.
     * @return An {@link Optional} describing flight entity, or an empty {@link Optional} if the flight is not found.
     * @throws DaoException if a data source access error or other errors.
     */
    FlightDto findFlightByID(long flightID) throws DaoException;

    List<FlightDto> findUnassignedFlights() throws DaoException;
    List<FlightDto> findAllFlights() throws DaoException;
    void updateFlightStatus(long flightID, long statusID) throws DaoException;
    void cancelFlightByID(long flightID) throws DaoException;
    void addFlight(FlightDto flightDto) throws DaoException;
    void updateFlight(FlightDto flightDto) throws DaoException;
    List<FlightDto> findFlightsBySearchQuery(SearchQuery query) throws DaoException;
}
