package com.epamtc.airline.service.impl;

import com.epamtc.airline.entity.User;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserServiceImplTest {
    private final UserService userService = new UserServiceImpl();
    private static final String USER_EMAIL = "test@mail.com";
    private static final String NOT_EXISTING_USER_EMAIL = "not_existing@mail.com";
    private static final String USER_PASSWORD = "111111";
    private static final String INCORRECT_PASSWORD = "222222";
    private static final long EXISTING_ID = 59L;
    private static final long INVALID_ID = -59L;

    @Test
    void existingUserLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(USER_EMAIL, USER_PASSWORD.toCharArray());
        if (!userTest.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(USER_EMAIL, userTest.get().getEmail());
    }

    @Test
    void notExistingUserLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(NOT_EXISTING_USER_EMAIL, USER_PASSWORD.toCharArray());
        Assertions.assertEquals(Optional.empty(), userTest);
    }

    @Test
    void incorrectPasswordLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(USER_EMAIL, INCORRECT_PASSWORD.toCharArray());
        Assertions.assertEquals(Optional.empty(), userTest);
    }

    @Test
    void nullEmailLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(null, USER_PASSWORD.toCharArray());
        Assertions.assertEquals(Optional.empty(), userTest);
    }

    @Test
    void nullPasswordLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(USER_EMAIL, null);
        Assertions.assertEquals(Optional.empty(), userTest);
    }

    @Test
    void nullCredentialsLoginTest() throws ServiceException {
        Optional<User> userTest = userService.login(null, null);
        Assertions.assertEquals(Optional.empty(), userTest);
    }

    @Test
    void takeUserByExistingIDTest() throws ServiceException {
        Optional<User> userTest = userService.takeUser(EXISTING_ID);
        if (!userTest.isPresent()) {
            Assertions.fail();
        }
        Assertions.assertEquals(EXISTING_ID, userTest.get().getID());
    }

    @Test
    void takeUserByInvalidIDTest() throws ServiceException {
        Optional<User> userTest = userService.takeUser(INVALID_ID);
        Assertions.assertFalse(userTest.isPresent());
    }
}
