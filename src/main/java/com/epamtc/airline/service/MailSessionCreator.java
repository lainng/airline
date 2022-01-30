package com.epamtc.airline.service;

import com.epamtc.airline.service.exception.ServiceException;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * This class is required for mail session creation.
 */
public class MailSessionCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERNAME_KEY = "mail.user.name";
    private static final String PASSWORD_KEY = "mail.user.password";
    private final Properties mailProperties;
    private final String username;
    private final String userPassword;

    public MailSessionCreator(Properties mailProperties) throws ServiceException {
        this.mailProperties = mailProperties;
        username = mailProperties.getProperty(USERNAME_KEY);
        userPassword = mailProperties.getProperty(PASSWORD_KEY);

        if (username == null || userPassword == null) {
            LOGGER.error("Wrong mail authentication properties.");
            throw new ServiceException("Wrong mail authentication properties.");
        }
    }

    public Session createSession() {
        return Session.getDefaultInstance(mailProperties,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, userPassword);
            }
        });
    }
}
