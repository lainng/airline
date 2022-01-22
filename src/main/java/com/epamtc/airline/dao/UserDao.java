package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Adds the created {@link User} instance to the data source.
     * @param userCreationDto
     * @throws DaoException if a data source access error or other errors.
     */
    void addUser(UserCreationDto userCreationDto) throws DaoException;

    /**
     * Fetches user with given email address.
     * @param email String containing email address.
     * @return An {@link Optional} describing user entity, or an empty {@link Optional} if the user is not found.
     * @throws DaoException if a data source access error or other errors.
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Updates user password.
     * @param userID
     * @param password New password.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateUserPassword(long userID, String password) throws DaoException;

    /**
     *
     * @return
     * @throws DaoException
     */
    List<User> findAllUsers() throws DaoException;

    /**
     *
     * @param userID
     * @return
     * @throws DaoException
     */
    Optional<User> findUserByID(long userID) throws DaoException;

    /**
     *
     * @param userCreationDto
     * @throws DaoException
     */
    void updateUser(UserCreationDto userCreationDto) throws DaoException;
}