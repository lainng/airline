package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.FlightStatusBuilder;
import com.epamtc.airline.entity.FlightStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightStatusBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final String EXPECTED_STRING = "test_name";

    @Test
    void buildTest() throws SQLException {
        ResultSet rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getLong(Column.FLIGHT_STATUS_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.STATUS_NAME)).thenReturn(EXPECTED_STRING);

        FlightStatus expected = new FlightStatus();
        expected.setID(EXPECTED_LONG);
        expected.setName(EXPECTED_STRING);

        FlightStatusBuilder builder = new FlightStatusBuilder();
        FlightStatus actual = builder.build(rsMock);

        assertEquals(expected, actual);
    }
}
