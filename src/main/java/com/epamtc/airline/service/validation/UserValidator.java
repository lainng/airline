package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.dto.UserCreationDto;

public interface UserValidator {
    /**
     * Validates login form data.
     * @param email User email.
     * @param password User password (not hashed).
     * @return {@code true} if data is valid otherwise returns {@code false}.
     */
    boolean loginValidate(String email, char[] password);

    /**
     * Validates sign up form data.
     * @param dto An {@link UserCreationDto} entity that contains user data.
     * @return {@code true} if data is valid otherwise returns {@code false}.
     */
    boolean signUpValidate(UserCreationDto dto);

    /**
     * Validates passwords.
     * @param dto An {@link UserCreationDto} entity that contains passwords.
     * @return {@code true} if passwords is valid otherwise returns {@code false}.
     */
    boolean changingPasswordValidate(UserCreationDto dto);

    /**
     * Validates the user's first and last name.
     * @param dto An {@link UserCreationDto} entity that contains user data.
     * @return {@code true} if data is valid otherwise returns {@code false}.
     */
    boolean changingUserInfoValidate(UserCreationDto dto);
}