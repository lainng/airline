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
     * @param dto
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
     * @param
     * @return {@code true} if password is changed, {@code false} otherwise.
     * @throws ServiceException if changing is impossible;
     */
    boolean editUserPassword(UserCreationDto dto) throws ServiceException;

    /**
     *
     * @return
     * @throws ServiceException
     */
    List<User> takeAllUsers() throws ServiceException;

    /**
     *
     * @param userID
     * @return
     * @throws ServiceException
     */
    Optional<User> takeUser(long userID) throws ServiceException;

    /**
     *
     * @param userCreationDto
     * @throws ServiceException
     */
    void editUser(UserCreationDto userCreationDto) throws ServiceException;
}