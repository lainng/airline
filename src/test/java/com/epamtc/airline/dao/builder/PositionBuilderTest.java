package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.PositionBuilder;
import com.epamtc.airline.entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final String EXPECTED_STRING = "test_name";

    @Test
    void buildTest() throws SQLException {
        ResultSet rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getLong(Column.POSITION_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.POSITION_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getLong(Column.POSITION_ROLE_ID)).thenReturn(EXPECTED_LONG);

        Position expected = new Position();
        expected.setID(EXPECTED_LONG);
        expected.setName(EXPECTED_STRING);
        expected.setRoleID(EXPECTED_LONG);

        PositionBuilder builder = new PositionBuilder();
        Position actual = builder.build(rsMock);

        Assertions.assertEquals(expected, actual);
    }
}
