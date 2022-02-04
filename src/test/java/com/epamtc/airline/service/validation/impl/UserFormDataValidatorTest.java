package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.validation.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserFormDataValidatorTest {
    private final UserValidator validator = new UserFormDataValidator();
    private static final String VALID_EMAIL = "mail@mail.com";
    private static final String INVALID_EMAIL = "mail@mail";
    private static final String VALID_PASSWORD = "secret";
    private static final String INVALID_PASSWORD = "empty";
    private static final String VALID_CYRILLIC_NAME = "Анна-Мария";
    private static final String INVALID_CYRILLIC_NAME = "анна_Мария";
    private static final String VALID_LATIN_NAME = "Ann-Maria";
    private static final String INVALID_LATIN_NAME = "ann_maria";
    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -1L;

    @Test
    void validateValidLoginCredentialsTest() {
        char[] password = VALID_PASSWORD.toCharArray();
        assertTrue(validator.loginValidate(VALID_EMAIL, password));
    }

    @Test
    void validateInvalidEmailTest() {
        char[] password = VALID_PASSWORD.toCharArray();
        assertFalse(validator.loginValidate(INVALID_EMAIL, password));
    }

    @Test
    void validateInvalidPasswordTest() {
        char[] password = INVALID_PASSWORD.toCharArray();
        assertFalse(validator.loginValidate(VALID_EMAIL, password));
    }

    @Test
    void validateInvalidLoginCredentialsTest() {
        char[] password = INVALID_PASSWORD.toCharArray();
        assertFalse(validator.loginValidate(INVALID_EMAIL, password));
    }

    @Test
    void validateValidSignUpDataTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(VALID_EMAIL);
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        dto.setPositionID(VALID_ID);
        assertTrue(validator.signUpValidate(dto));
    }

    @Test
    void validateInvalidEmailSignUpTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(INVALID_EMAIL);
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        dto.setPositionID(VALID_ID);
        assertFalse(validator.signUpValidate(dto));
    }

    @Test
    void validateInvalidPasswordSignUpTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(INVALID_EMAIL);
        dto.setPassword(INVALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        dto.setPositionID(VALID_ID);
        assertFalse(validator.signUpValidate(dto));
    }

    @Test
    void validateInvalidNameSignUpTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(INVALID_EMAIL);
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        dto.setFirstName(INVALID_LATIN_NAME);
        dto.setLastName(INVALID_CYRILLIC_NAME);
        dto.setPositionID(VALID_ID);
        assertFalse(validator.signUpValidate(dto));
    }

    @Test
    void validateInvalidPositionIDSignUpTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(INVALID_EMAIL);
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        dto.setPositionID(INVALID_ID);
        assertFalse(validator.signUpValidate(dto));
    }

    @Test
    void validateNotEqualPasswordsSignUpTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setEmail(INVALID_EMAIL);
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD + VALID_PASSWORD);
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        dto.setPositionID(INVALID_ID);
        assertFalse(validator.signUpValidate(dto));
    }

    @Test
    void validateValidPasswordsTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD);
        assertTrue(validator.changingPasswordValidate(dto));
    }

    @Test
    void validateInvalidPasswordsTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setPassword(INVALID_PASSWORD);
        dto.setPasswordConfirmation(INVALID_PASSWORD);
        assertFalse(validator.changingPasswordValidate(dto));
    }

    @Test
    void validateNotEqualPasswordsTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setPassword(VALID_PASSWORD);
        dto.setPasswordConfirmation(VALID_PASSWORD + VALID_PASSWORD);
        assertFalse(validator.changingPasswordValidate(dto));
    }

    @Test
    void validateValidUserChangingDataTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setFirstName(VALID_CYRILLIC_NAME);
        dto.setLastName(VALID_LATIN_NAME);
        assertTrue(validator.changingUserInfoValidate(dto));
    }

    @Test
    void validateInvalidUserChangingDataTest() {
        UserCreationDto dto = new UserCreationDto();
        dto.setFirstName(INVALID_LATIN_NAME);
        dto.setLastName(VALID_CYRILLIC_NAME);
        assertFalse(validator.changingUserInfoValidate(dto));
    }
}
