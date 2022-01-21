package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.BasicQuery;
import com.epamtc.airline.dao.BatchQuery;
import com.epamtc.airline.dao.CrewDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.entity.dto.CrewDto;

import java.util.ArrayList;
import java.util.List;

public class CrewDaoImpl extends AbstractDao<CrewDto> implements CrewDao {
    private static final String QUERY_GET_CREW_BY_FLIGHT = "SELECT C.flight_id, EC.crew_id, E.employee_id, E.first_name, E.last_name, P.position_id, P.name, E.email, E.password, P.role_id " +
            "FROM employee E " +
            "JOIN position P on P.position_id = E.position_id " +
            "JOIN employee_in_crew EC on E.employee_id = EC.employee_id " +
            "JOIN crew C on C.crew_id = EC.crew_id " +
            "WHERE flight_id = ?;";
    private static final String QUERY_GET_ALL_FLIGHTS_WITH_CREWS = "SELECT C.flight_id, EC.crew_id, E.employee_id, E.first_name, E.last_name, P.position_id, P.name, E.email, E.password, P.role_id " +
            "FROM employee E " +
            "JOIN position P on P.position_id = E.position_id " +
            "JOIN employee_in_crew EC on E.employee_id = EC.employee_id " +
            "JOIN crew C on C.crew_id = EC.crew_id;";
    private static final String QUERY_UPDATE_FLIGHT_IN_CREW = "UPDATE crew SET flight_id = ? WHERE crew_id = ?;";
    private static final String QUERY_DELETE_STAFF_IN_CREW = "DELETE FROM employee_in_crew WHERE crew_id = ?";
    private static final String QUERY_ADD_STAFF_TO_UPDATED_CREW = "INSERT INTO employee_in_crew(crew_id, employee_id, employee_confirmation) VALUES (?, ?, 0);";
    private static final String QUERY_ADD_STAFF_TO_NEW_CREW = "INSERT INTO employee_in_crew(crew_id, employee_id, employee_confirmation) VALUES ((select crew_id from crew where flight_id = ?), ?, 0);";
    private static final String QUERY_INSERT_FLIGHT_IN_CREW = "INSERT INTO crew (flight_id) VALUES (?);";
    private static final String QUERY_DELETE_CREW_BY_ID = "DELETE FROM crew WHERE crew_id = ?;";
    private static final String QUERY_GET_CREW_BY_ID = "SELECT C.flight_id, EC.crew_id, E.employee_id, E.first_name, E.last_name, P.position_id, P.name, E.email, E.password, P.role_id " +
            "FROM employee E " +
            "JOIN position P on P.position_id = E.position_id " +
            "JOIN employee_in_crew EC on E.employee_id = EC.employee_id " +
            "JOIN crew C on C.crew_id = EC.crew_id " +
            "WHERE EC.crew_id = ?;";

    public CrewDaoImpl() {
        super(EntityBuilderFactory.getInstance().getCrewDtoBuilder());
    }

    @Override
    public CrewDto findCrewByFlightID(long flightID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_CREW_BY_FLIGHT,
                flightID
        );
    }

    @Override
    public CrewDto findCrewByID(long crewID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_CREW_BY_ID,
                crewID
        );
    }

    public List<CrewDto> findAllCrews() throws DaoException {
        return queryExecutor.executeQuery(
                QUERY_GET_ALL_FLIGHTS_WITH_CREWS
        );
    }
    @Override
    public void updateCrew(CrewCreationDto crewCreationDto) throws DaoException {
        List<Object[]> batch = new ArrayList<>();
        for (long userID : crewCreationDto.getMembers()) {
            batch.add(new Object[] {crewCreationDto.getID(), userID});
        }
        queryExecutor.executeTransactionUpdate(
                new BasicQuery(
                        QUERY_UPDATE_FLIGHT_IN_CREW,
                        crewCreationDto.getAssignedFlightID(),
                        crewCreationDto.getID()),
                new BasicQuery(
                        QUERY_DELETE_STAFF_IN_CREW,
                        crewCreationDto.getID()),
                new BatchQuery(
                        QUERY_ADD_STAFF_TO_UPDATED_CREW,
                        batch
                )
        );
    }
    @Override
    public void insertNewCrew(CrewCreationDto crewCreationDto) throws DaoException {
        List<Object[]> batch = new ArrayList<>();
        for (long userID : crewCreationDto.getMembers()) {
            batch.add(new Object[] {crewCreationDto.getAssignedFlightID(), userID});
        }
        queryExecutor.executeTransactionUpdate(
                new BasicQuery(
                        QUERY_INSERT_FLIGHT_IN_CREW,
                        crewCreationDto.getAssignedFlightID()),
                new BatchQuery(
                        QUERY_ADD_STAFF_TO_NEW_CREW,
                        batch
                )
        );
    }
    @Override
    public int deleteCrew(long crewID) throws DaoException {
        return queryExecutor.executeUpdate(
                QUERY_DELETE_CREW_BY_ID,
                crewID
        );
    }
}