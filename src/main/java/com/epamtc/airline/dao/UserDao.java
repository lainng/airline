package com.epamtc.airline.dao;

import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Adds the created {@link User} instance to the data source.
     * @param userCreationDto An {@link UserCreationDto} instance that contains user information.
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
     * Updates the user password.
     * @param userID User ID in data source.
     * @param password New password.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateUserPassword(long userID, String password) throws DaoException;

    /**
     * Fetches all users.
     * @return The {@link List} of the {@link User} that contains in the data source.
     * @throws DaoException if a data source access error or other errors.
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Fetches user by its ID.
     * @param userID User ID in data source.
     * @return An {@link User} instance that linked with specified ID.
     * @throws DaoException if a data source access error or other errors.
     */
    Optional<User> findUserByID(long userID) throws DaoException;

    /**
     * Update user information
     * @param userCreationDto An {@link UserCreationDto} instance that contains changing information.
     * @throws DaoException if a data source access error or other errors.
     */
    void updateUser(UserCreationDto userCreationDto) throws DaoException;
}