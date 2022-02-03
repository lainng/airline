package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.CityBuilder;
import com.epamtc.airline.entity.City;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final String EXPECTED_STRING = "test_name";

    @Test
    void buildTest() throws SQLException {
        ResultSet rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getLong(Column.CITY_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.CITY_NAME)).thenReturn(EXPECTED_STRING);

        City expected = new City();
        expected.setID(EXPECTED_LONG);
        expected.setName(EXPECTED_STRING);

        CityBuilder builder = new CityBuilder();
        City actual = builder.build(rsMock);

        assertEquals(expected, actual);
    }
}
