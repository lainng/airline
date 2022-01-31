package com.epamtc.airline.service;

import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Authenticates user.
     * @param email Email address.
     * @param password Password (not hashed).
     * @return An {@link Optional} describing user entity, or an empty {@link Optional} if authentication did not happen.
     * @throws ServiceException if authentication is impossible.
     */
    Optional<User> login(String email, char[] password) throws ServiceException;

    /**
     * Registers new user in system.
     * @param dto A {@link UserCreationDto} entity that contains the raw information about user creating.
     * @return {@code true} if user is registered, {@code false} otherwise.
     * @throws ServiceException if registering is impossible.
     */
    boolean signUp(UserCreationDto dto) throws ServiceException;

    /**
     * Retrieves a {@link List} of {@link Position} entities that user can occupy.
     * @return A {@link List} of {@link Position} entities that user can occupy, or an empty {@link List} if positions are impossible.
     * @throws ServiceException if retrieving of positions is impossible.
     */
    List<Position> takePositions() throws ServiceException;

    /**
     * Changes a user password.
     * @param dto A {@link UserCreationDto} entity that contains user passwords.
     * @return {@code true} if password is changed, {@code false} otherwise.
     * @throws ServiceException if changing is impossible.
     */
    boolean editUserPassword(UserCreationDto dto) throws ServiceException;

    /**
     * Retrieves all users.
     * @return The {@link List} of user or the empty {@link List} if users is not found.
     * @throws ServiceException if retrieving of the user's list is impossible.
     */
    List<User> takeAllUsers() throws ServiceException;

    /**
     * Retrieves a user entity by a specified ID.
     * @param userID The ID of the user.
     * @return A {@link Optional} describing user entity, or an empty {@link Optional} if the user is not found.
     * @throws ServiceException if retrieving of the user's entity is impossible.
     */
    Optional<User> takeUser(long userID) throws ServiceException;

    /**
     * Edited user's entity from the data source.
     * @param userCreationDto A {@link UserCreationDto} entity that contains changing information.
     * @throws ServiceException if editing a user's entity is impossible.
     */
    boolean editUser(UserCreationDto userCreationDto) throws ServiceException;
}