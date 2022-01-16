package com.epamtc.airline.service.validation;

import com.epamtc.airline.entity.dto.UserCreationDto;

public interface UserValidator {
    boolean loginValidate(String email, char[] password);
    boolean signUpValidate(UserCreationDto dto);
    boolean changingPasswordValidate(String password, String confirmPassword);
    boolean changingUserInfoValidate(String firstName, String lastName);
}
