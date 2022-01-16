package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.PositionDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Position;

import java.util.List;

public class PositionDaoImpl extends AbstractDao<Position> implements PositionDao {
    private static final String QUERY_GET_ALL_POSITIONS = "SELECT * FROM position;";
    private static final String QUERY_GET_POSITION_BY_ID = "SELECT * FROM position WHERE position_id = ?;";

    public PositionDaoImpl() {
        super(EntityBuilderFactory.getInstance().getPositionBuilder());
    }

    @Override
    public Position takePositionByID(long positionID) throws DaoException {
        return queryExecutor.executeSingleEntityQuery(
                QUERY_GET_POSITION_BY_ID,
                positionID
        );
    }

    @Override
    public List<Position> takeAllPositions() throws DaoException {
        return queryExecutor.executeQuery(
                QUERY_GET_ALL_POSITIONS
        );
    }
}