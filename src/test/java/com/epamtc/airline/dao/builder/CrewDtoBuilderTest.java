package com.epamtc.airline.dao.builder;

import com.epamtc.airline.dao.builder.impl.CrewDtoBuilder;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.CrewDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrewDtoBuilderTest {
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
        Mockito.when(rsMock.next()).thenReturn(true, false);
        Mockito.when(rsMock.getLong(Column.POSITION_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.POSITION_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getLong(Column.POSITION_ROLE_ID)).thenReturn(EXPECTED_LONG);

        Mockito.when(rsMock.getLong(Column.EMPLOYEE_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_FIRST_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_LAST_NAME)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_EMAIL)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getString(Column.EMPLOYEE_PASSWORD)).thenReturn(EXPECTED_STRING);
        Mockito.when(rsMock.getBoolean(Column.EMPLOYEE_CONFIRMATION)).thenReturn(EXPECTED_BOOLEAN);

        Mockito.when(rsMock.getLong(Column.EMPLOYEE_CREW_ID)).thenReturn(EXPECTED_LONG);
        Mockito.when(rsMock.getLong(Column.CREW_FLIGHT_ID)).thenReturn(EXPECTED_LONG);
    }

    @Test
    void buildTest() throws SQLException {
        CrewDto expected = new CrewDto();
        expected.setID(EXPECTED_LONG);
        expected.setAssignedFlightID(EXPECTED_LONG);
        expected.setMembers(createTwoCrewMembers());

        CrewDtoBuilder builder = new CrewDtoBuilder();
        CrewDto actual = builder.build(rsMock);

        Assertions.assertEquals(expected, actual);
    }

    private List<User> createTwoCrewMembers() {
        List<User> members = new ArrayList<>();

        Position testPosition = new Position();
        testPosition.setID(EXPECTED_LONG);
        testPosition.setName(EXPECTED_STRING);
        testPosition.setRoleID(EXPECTED_LONG);

        User user = new User();
        user.setID(EXPECTED_LONG);
        user.setFirstName(EXPECTED_STRING);
        user.setLastName(EXPECTED_STRING);
        user.setEmail(EXPECTED_STRING);
        user.setPassword(EXPECTED_STRING);
        user.setPosition(testPosition);
        user.setConfirmedAssignedFlight(EXPECTED_BOOLEAN);

        members.add(user);
        members.add(user);

        return members;
    }
}
