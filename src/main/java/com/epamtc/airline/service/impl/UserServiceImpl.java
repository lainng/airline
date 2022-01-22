package com.epamtc.airline.service.impl;

import com.epamtc.airline.command.UserRole;
import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.PositionDao;
import com.epamtc.airline.dao.UserDao;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Position;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.UserCreationDto;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import com.epamtc.airline.service.validation.UserValidator;
import com.epamtc.airline.service.validation.ValidatorFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<User> login(String email, char[] password) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (!userValidator.loginValidate(email, password)) {
            return Optional.empty();
        }
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!DigestUtils.md5Hex(String.valueOf(password)).equalsIgnoreCase(user.getPassword())) {
                    return Optional.empty();
                }
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to login. {}", e.getMessage());
            throw new ServiceException("Unable to login.", e);
        }
        return optionalUser;
    }
    @Override
    public boolean signUp(UserCreationDto userCreationDto) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (!userValidator.signUpValidate(userCreationDto)) {
            return false;
        }
        try {
            Optional<User> optionalUserFromDB = userDao.findUserByEmail(userCreationDto.getEmail());
            if (!optionalUserFromDB.isPresent()) {
                userDao.addUser(userCreationDto);
                return true;
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to sign up. {}", e.getMessage());
            throw new ServiceException("Unable to sign up.", e);
        }
        return false;
    }
    @Override
    public List<Position> takePositions() throws ServiceException {
        PositionDao positionDao = DaoFactory.getInstance().getPositionDao();
        List<Position> positions;
        try {
            positions = positionDao.findAllPositions();
            positions.removeIf(position -> position.getRoleID() == UserRole.ADMIN);
        } catch (DaoException e) {
            LOGGER.error("Unable to take all positions. {}", e.getMessage());
            throw new ServiceException("Unable to take all positions.", e);
        }
        return positions;
    }
    @Override
    public boolean editUserPassword(UserCreationDto dto) throws ServiceException {
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        UserDao userDao = DaoFactory.getInstance().getUserDao();

        boolean isPasswordsValid = userValidator.changingPasswordValidate(dto.getPassword(), dto.getPasswordConfirmation());
        if (!isPasswordsValid) {
            return false;
        }

        String hashedPassword = DigestUtils.md5Hex(dto.getPassword());
        try {
            userDao.updateUserPassword(dto.getID(), hashedPassword);
        } catch (DaoException e) {
            LOGGER.error("Unable to update the user password. {}", e.getMessage());
            throw new ServiceException("Unable to update the user password.", e);
        }
        return true;
    }
    @Override
    public List<User> takeAllUsers() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            LOGGER.error("Unable to take users. {}", e.getMessage());
            throw new ServiceException("Unable to take users.", e);
        }
        return users;
    }
    @Override
    public Optional<User> takeUser(long userID) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findUserByID(userID);
        } catch (DaoException e) {
            LOGGER.error("Unable to get the user by its ID. {}", e.getMessage());
            throw new ServiceException("Unable to get the user by its ID.", e);
        }
        return optionalUser;
    }
    @Override
    public void editUser(UserCreationDto userCreationDto) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.updateUser(userCreationDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to update user information. {}", e.getMessage());
            throw new ServiceException("Unable to update user information.", e);
        }
    }
}