package com.epamtc.airline.dao.builder.impl;

import com.epamtc.airline.dao.builder.Column;
import com.epamtc.airline.dao.builder.EntityBuilder;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.CrewDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrewDtoBuilder implements EntityBuilder<CrewDto> {
    @Override
    public CrewDto build(ResultSet resultSet) throws SQLException {
        UserBuilder userBuilder = EntityBuilderFactory.getInstance().getUserBuilder();
        CrewDto crewDto = new CrewDto();
        crewDto.setAssignedFlightID(resultSet.getLong(Column.CREW_FLIGHT_ID));
        crewDto.setID(resultSet.getLong(Column.EMPLOYEE_CREW_ID));
        List<User> crewMembers = new ArrayList<>();
        do {
            crewMembers.add(userBuilder.build(resultSet));
        }
        while (resultSet.next() && resultSet.getLong(Column.EMPLOYEE_CREW_ID) == crewDto.getID());
        crewDto.setMembers(crewMembers);
        resultSet.previous();

        return crewDto;
    }
}