package com.epamtc.airline.dao.impl;

import com.epamtc.airline.dao.AbstractDao;
import com.epamtc.airline.dao.UserDao;
import com.epamtc.airline.dao.builder.EntityBuilderFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String QUERY_INSERT_USER = "INSERT INTO employee (first_name, last_name, position_id, email, password) " +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String QUERY_GET_USER_BY_EMAIL = "SELECT E.employee_id, E.first_name, E.last_name, P.position_id, P.name, P.role_id, E.email, E.password " +
            "FROM employee E JOIN position P on P.position_id = E.position_id " +
            "WHERE email = ?;";
    private static final String QUERY_GET_USER_BY_ID = "SELECT E.employee_id, E.first_name, E.last_name, P.position_id, P.name, P.role_id, E.email, E.password " +
            "FROM employee E JOIN position P on P.position_id = E.position_id " +
            "WHERE E.employee_id = ?;";
    private static final String QUERY_UPDATE_USER = "UPDATE employee SET first_name = ?, last_name = ?, position_id = ? WHERE employee_id = ?;";
    private static final String QUERY_UPDATE_PASSWORD_BY_EMAIL = "UPDATE employee SET password = ? WHERE employee_id = ?;";
    private static final String QUERY_GET_ALL_USERS = "SELECT E.employee_id, E.first_name, E.last_name, P.position_id, P.name, P.role_id, E.email, E.password " +
            "FROM employee E JOIN position P on P.position_id = E.position_id;";

    public UserDaoImpl() {
        super(EntityBuilderFactory.getInstance().getUserBuilder());
    }

    @Override
    public void addUser(UserCreationDto userCreationDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_INSERT_USER,
                userCreationDto.getFirstName(),
                userCreationDto.getLastName(),
                userCreationDto.getPositionID(),
                userCreationDto.getEmail(),
                userCreationDto.getPassword()
        );
    }
    @Override
    public Optional<User> getUserByEmail(String email) throws DaoException {
        return Optional.ofNullable(
                queryExecutor.executeSingleEntityQuery(
                    QUERY_GET_USER_BY_EMAIL,
                    email
                )
        );
    }

    @Override
    public void updateUserPassword(long userID, String password) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_PASSWORD_BY_EMAIL,
                password,
                userID
        );
    }
    @Override
    public List<User> findAllUsers() throws DaoException {
        return queryExecutor.executeQuery(
                QUERY_GET_ALL_USERS
        );
    }

    @Override
    public Optional<User> findUserByID(long userID) throws DaoException {
        return Optional.ofNullable(
                queryExecutor.executeSingleEntityQuery(
                        QUERY_GET_USER_BY_ID,
                        userID
                )
        );
    }
    @Override
    public void updateUser(UserCreationDto userCreationDto) throws DaoException {
        queryExecutor.executeUpdate(
                QUERY_UPDATE_USER,
                userCreationDto.getFirstName(),
                userCreationDto.getLastName(),
                userCreationDto.getPositionID(),
                userCreationDto.getID()
        );
    }
}