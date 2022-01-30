package com.epamtc.airline.service.validation.impl;

import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.validation.UserValidator;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserFormDataValidator implements UserValidator {
    private static final String NAME_REGEXP = "^([A-ZА-Я][-,a-zа-я. ']+)+";
    private static final String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private static final int PASSWORD_LENGTH = 6;

    @Override
    public boolean loginValidate(String email, char[] password) {
        if (!checkParameters(email, password)) return false;
        return Pattern.matches(EMAIL_REGEXP, email.toLowerCase()) && password.length >= PASSWORD_LENGTH;
    }

    @Override
    public boolean signUpValidate(UserCreationDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String confirmPassword = dto.getPasswordConfirmation();
        String firstName = dto.getFirstName();
        String lastName = dto.getLastName();
        long positionID = dto.getPositionID();
        if (!checkParameters(email, password, confirmPassword, firstName, lastName)) return false;
        return Pattern.matches(NAME_REGEXP, firstName)
                && Pattern.matches(NAME_REGEXP, lastName)
                && Pattern.matches(EMAIL_REGEXP, email.toLowerCase())
                && validatePasswords(password, confirmPassword)
                && positionID > 0;
    }

    @Override
    public boolean changingPasswordValidate(UserCreationDto dto) {
        String password = dto.getPassword();
        String confirmPassword = dto.getPasswordConfirmation();
        if (!checkParameters(password, confirmPassword)) return false;
        return validatePasswords(password, confirmPassword);
    }

    @Override
    public boolean changingUserInfoValidate(UserCreationDto dto) {
        String firstName = dto.getFirstName();
        String lastName = dto.getLastName();
        if (!checkParameters(firstName, lastName)) return false;
        return Pattern.matches(NAME_REGEXP, firstName) && Pattern.matches(NAME_REGEXP, lastName);
    }

    private boolean checkParameters(Object... parameters) {
        for (Object parameter : parameters) {
            if (parameter == null) {
                return false;
            }
        }
        return true;
    }
    private boolean validatePasswords(String password, String confirmPassword) {
        if (password.length() < PASSWORD_LENGTH && confirmPassword.length() < PASSWORD_LENGTH) return false;
        return Objects.equals(password, confirmPassword);
    }
}
