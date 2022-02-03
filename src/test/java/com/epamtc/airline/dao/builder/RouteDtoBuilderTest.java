package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.RouteDtoBuilder;
import com.epamtc.airline.entity.dto.RouteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class RouteDtoBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final int EXPECTED_INT = 1;
    private static final Time EXPECTED_TIME = new Time(1L);
    private ResultSet rsMock;

    @BeforeEach
    void setUp() throws SQLException {
        rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getLong(Column.ROUTE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getLong(Column.ROUTE_DEPARTURE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getLong(Column.ROUTE_DESTINATION_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getInt(Column.ROUTE_DISTANCE)).thenReturn(EXPECTED_INT);
        Mockito.when(rsMock.getTime(Column.ROUTE_DURATION)).thenReturn(EXPECTED_TIME);
    }

    @Test
    void buildTest() throws SQLException {
        RouteDto expected = new RouteDto();
        expected.setID(EXPECTED_LONG);
        expected.setDepartureID(EXPECTED_LONG);
        expected.setDestinationID(EXPECTED_LONG);
        expected.setDistance(EXPECTED_INT);
        expected.setDuration(EXPECTED_TIME);

        RouteDtoBuilder builder = new RouteDtoBuilder();
        RouteDto actual = builder.build(rsMock);

        Assertions.assertEquals(expected, actual);
    }
}
