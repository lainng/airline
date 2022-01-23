package com.epamtc.airline.dao.impl;

import com.epamtc.airline.command.FlightCondition;
import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.FlightDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.SearchQuery;

import java.util.List;

public class FlightDaoImpl extends AbstractDao<FlightDto> implements FlightDao {

    private static final String QUERY_GET_USER_FLIGHTS = "SELECT f.flight_id, f.route_id, f.plane_id, f.departure_time, f.status_id, s.name, ec.employee_confirmation " +
            "FROM flight f " +
            "JOIN status s on s.status_id = f.status_id " +
            "JOIN crew c on f.flight_id = c.flight_id " +
            "JOIN employee_in_crew ec on c.crew_id = ec.crew_id " +
            "WHERE employee_id = ?;";
    private static final String QUERY_CONFIRM_FLIGHT = "UPDATE employee_in_crew " +
            "JOIN crew C ON C.crew_id = employee_in_crew.crew_id " +
            "SET employee_confirmation = 1 " +
            "WHERE employee_in_crew.employee_id = ? AND C.flight_id = ?;";
    private static final String QUERY_GET_FLIGHT_BY_ID = "SELECT F.flight_id, F.route_id, F.plane_id, F.departure_time, " +
            "F.status_id, S.name " +
            "FROM flight F " +
            "JOIN status S ON F.status_id = S.status_id " +
            "WHERE F.flight_id = ?;";
    private static final String QUERY_GET_UNASSIGNED_FLIGHTS = "SELECT F.flight_id , F.route_id, F.plane_id, F.departure_time, F.status_id, S.name " +
            "FROM flight F " +
            "LEFT JOIN crew C on F.flight_id = C.flight_id " +
            "JOIN status S ON F.status_id = S.status_id " +
            "WHERE C.flight_id IS NULL;";
    private static final String QUERY_GET_ALL_FLIGHTS = "SELECT F.flight_id, F.route_id, F.plane_id, F.departure_time, " +
            "F.status_id, S.name " +
            "FROM flight F " +
            "JOIN status S ON F.status_id = S.status_id;";
    private static final String QUERY_UPDATE_FLIGHT_STATUS = "UPDATE flight SET status_id = ? WHERE flight_id = ?;";
    private static final String QUERY_REMOVE_FLIGHT_FROM_CREW = "DELETE FROM crew WHERE flight_id = ?";
    private static final String QUERY_INSERT_FLIGHT = "INSERT INTO flight (route_id, plane_id, departure_time, status_id) VALUES (?, ?, ?, ?);";
    private static final String QUERY_UPDATE_FLIGHT_BY_ID = "UPDATE flight SET route_id = ?, plane_id = ?, departure_time = ? WHERE flight_id = ?;";
    private static final String QUERY_GET_FLIGHTS_BY_SEARCH_QUERY = "SELECT F.flight_id, F.route_id, F.plane_id, F.departure_time, " +
            "F.status_id, S.name " +
            "FROM flight F " +
            "JOIN status S ON F.status_id = S.status_id " +
            "JOIN route R on R.route_id = F.route_id " +
            "WHERE r.departure_city_id = ? AND r.destination_city_id = ? " +
            "AND F.departure_time BETWEEN ? AND ?;";

    public FlightDaoImpl() {
        super(EntityBuilderFactory.getInstance().getFlightDtoBuilder());
    }

    @Override
    public List<FlightDto> findUserFlights(long userID) throws DaoException {
        return queryExecutor.executeQuery(
                QUERY_GET_USER_FLIGHTS,
                userID
        );
    }
    @Override
    public void confirmFlightByID(long flightID, long userID) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_CONFIRM_FLIGHT,
                userID,
                flightID
        );
    }
    @Override
    public FlightDto findFlightByID(long flightID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_FLIGHT_BY_ID,
                flightID
        );
    }
    @Override
    public List<FlightDto> findUnassignedFlights() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_UNASSIGNED_FLIGHTS);
    }
    @Override
    public List<FlightDto> findAllFlights() throws DaoException {
        return queryExecutor.executeQuery(QUERY_GET_ALL_FLIGHTS);
    }
    @Override
    public void updateFlightStatus(long flightID, long statusID) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_FLIGHT_STATUS,
                statusID,
                flightID
        );
    }
    @Override
    public void cancelFlightByID(long flightID) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_REMOVE_FLIGHT_FROM_CREW,
                flightID
        );
        updateFlightStatus(flightID, FlightCondition.CANCELED);
    }
    @Override
    public void addFlight(FlightDto flightDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_INSERT_FLIGHT,
                flightDto.getRouteID(),
                flightDto.getPlaneID(),
                flightDto.getDepartureTime(),
                flightDto.getFlightStatus().getID()
        );
    }
    @Override
    public void updateFlight(FlightDto flightDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_FLIGHT_BY_ID,
                flightDto.getRouteID(),
                flightDto.getPlaneID(),
                flightDto.getDepartureTime(),
                flightDto.getID()
        );
    }
    @Override
    public List<FlightDto> findFlightsBySearchQuery(SearchQuery searchQuery) throws DaoException {
        return queryExecutor.executeQuery(
                QUERY_GET_FLIGHTS_BY_SEARCH_QUERY,
                searchQuery.getDepartmentID(),
                searchQuery.getDestinationID(),
                searchQuery.getDeptDate(),
                searchQuery.getDestDate()
        );
    }
}