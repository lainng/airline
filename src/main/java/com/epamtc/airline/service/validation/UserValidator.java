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
     *
     * @param dto
     * @return
     */
    boolean signUpValidate(UserCreationDto dto);

    /**
     *
     * @param dto
     * @return
     */
    boolean changingPasswordValidate(UserCreationDto dto);

    /**
     *
     * @param dto
     * @return
     */
    boolean changingUserInfoValidate(UserCreationDto dto);
}
