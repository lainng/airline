package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.UserBuilder;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserBuilderTest {
    private static final long EXPECTED_LONG = 1L;
    private static final String EXPECTED_STRING = "test_name";
    private static final boolean EXPECTED_BOOLEAN = false;
    private ResultSet rsMock;

    @BeforeEach
    void setUp() throws SQLException {
        ResultSetMetaData rsMetaMock = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(rsMetaMock.getColumnCount()).thenReturn(1);
        Mockito.when(rsMetaMock.getColumnName(1)).thenReturn(Column.EMPLOYEE_CONFIRMATION);

        rsMock = Mockito.mock(ResultSet.class);
        Mockito.when(rsMock.getMetaData()).thenReturn(rsMetaMock);
        Mockito.when(rsMock.getLong(Column.EMPLOYEE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_FIRST_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_LAST_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_EMAIL)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_PASSWORD)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getBoolean(Column.EMPLOYEE_CONFIRMATION)).thenReturn(EXPECTED_BOOLEAN);

        Mockito.when(rsMock.getLong(Column.POSITION_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.POSITION_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getLong(Column.POSITION_ROLE_ID)).thenReturn(EXPECTED_LONG);
    }

    @Test
    void buildTest() throws SQLException {
        Position testPosition = new Position();
        testPosition.setID(EXPECTED_LONG);
        testPosition.setName(EXPECTED_STRING);
        testPosition.setRoleID(EXPECTED_LONG);

        User expected = new User();
        expected.setID(EXPECTED_LONG);
        expected.setFirstName(EXPECTED_STRING);
        expected.setLastName(EXPECTED_STRING);
        expected.setEmail(EXPECTED_STRING);
        expected.setPassword(EXPECTED_STRING);
        expected.setPosition(testPosition);
        expected.setConfirmedAssignedFlight(EXPECTED_BOOLEAN);

        UserBuilder builder = new UserBuilder();
        User actual = builder.build(rsMock);

        Assertions.assertEquals(expected, actual);
    }
}
