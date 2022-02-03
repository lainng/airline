package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.FlightDtoBuilder;
import com.epamtc.airline.entity.FlightStatus;
import com.epamtc.airline.entity.dto.FlightDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FlightDtoBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final String EXPECTED_STRING = "test_name";
    private static final boolean EXPECTED_BOOLEAN = false;
    private static final Timestamp EXPECTED_TIMESTAMP = new Timestamp(1L);
    private ResultSet rsMock;

    @BeforeEach
    void setUp() throws SQLException {
        ResultSetMetaData rsMetaMock = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(rsMetaMock.getColumnCount()).thenReturn(1);
        Mockito.when(rsMetaMock.getColumnName(1)).thenReturn(Column.EMPLOYEE_CONFIRMATION);

        rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getMetaData()).thenReturn(rsMetaMock);
        Mockito.when(rsMock.getLong(Column.FLIGHT_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getLong(Column.FLIGHT_ROUTE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getLong(Column.FLIGHT_PLANE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getTimestamp(Column.FLIGHT_DEPARTURE_TIME)).thenReturn(EXPECTED_TIMESTAMP);

        Mockito.when(rsMock.getLong(Column.FLIGHT_STATUS_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.STATUS_NAME)).thenReturn(EXPECTED_STRING);
    }

    @Test
    void buildTest() throws SQLException {
        FlightStatus flightStatusTest = new FlightStatus();
        flightStatusTest.setID(EXPECTED_LONG);
        flightStatusTest.setName(EXPECTED_STRING);

        FlightDto expected = new FlightDto();
        expected.setID(EXPECTED_LONG);
        expected.setRouteID(EXPECTED_LONG);
        expected.setPlaneID(EXPECTED_LONG);
        expected.setDepartureTime(EXPECTED_TIMESTAMP);
        expected.setFlightStatus(flightStatusTest);
        expected.setConfirmed(EXPECTED_BOOLEAN);

        FlightDtoBuilder builder = new FlightDtoBuilder();
        FlightDto actual = builder.build(rsMock);

        Assertions.assertEquals(expected, actual);
    }
}
