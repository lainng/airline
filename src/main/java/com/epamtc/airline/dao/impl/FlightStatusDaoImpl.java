package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.FlightStatusDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.FlightStatus;

public class FlightStatusDaoImpl extends AbstractDao<FlightStatus> implements FlightStatusDao {
    private static final String QUERY_GET_FLIGHT_STATUS_BY_ID = "select * from status where status_id = ?;";

    public FlightStatusDaoImpl() {
        super(EntityBuilderFactory.getInstance().getFlightStatusBuilder());
    }

    @Override
    public FlightStatus takeFlightStatusByID(long statusID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_FLIGHT_STATUS_BY_ID,
                statusID
        );
    }
}
