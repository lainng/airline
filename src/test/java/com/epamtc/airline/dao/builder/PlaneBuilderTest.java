package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.PlaneBuilder;
import com.epamtc.airline.entity.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaneBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final int EXPECTED_INT = 1;
    private static final String EXPECTED_STRING = "test_name";
    private ResultSet rsMock;

    @BeforeEach
    void setUp() throws SQLException {
        rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getLong(Column.PLANE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.PLANE_MODEL)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getInt(Column.PLANE_FLIGHT_RANGE)).thenReturn(EXPECTED_INT);
        Mockito.when(rsMock.getInt(Column.PLANE_FLYING_HOURS)).thenReturn(EXPECTED_INT);
        Mockito.when(rsMock.getInt(Column.PLANE_PASSENGER_CAPACITY)).thenReturn(EXPECTED_INT);
    }

    @Test
    void buildTest() throws SQLException {
        Plane expected = new Plane();
        expected.setID(EXPECTED_LONG);
        expected.setModel(EXPECTED_STRING);
        expected.setFlyingHours(EXPECTED_INT);
        expected.setPassengerCapacity(EXPECTED_INT);
        expected.setFlightRange(EXPECTED_INT);

        PlaneBuilder builder = new PlaneBuilder();
        Plane actual = builder.build(rsMock);
        assertEquals(expected, actual);
    }
}
