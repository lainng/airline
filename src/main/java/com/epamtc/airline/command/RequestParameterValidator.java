package com.epamtc.airline.command;

import java.util.regex.Pattern;

/**
 * This class provides a request parameters validator.
 */
public class RequestParameterValidator {
    private static final String NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";
    private static final String DATE_PATTERN = "^([0-2][0-9]|(3)[0-1])(\\.)(((0)[0-9])|((1)[0-2]))(\\.)\\d{4}$";

    public RequestParameterValidator() {}

    public boolean isNumeric(String parameter) {
        if (isNull(parameter) || parameter.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        return pattern.matcher(parameter).matches();
    }

    public boolean isEmpty(String parameter) {
        return parameter.isEmpty();
    }

    public boolean isValidID(String ID) {
        return isNumeric(ID) && isGreaterZero(ID);
    }

    public boolean isValidDate(String date) {
        if (isNull(date) || date.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        return pattern.matcher(date).matches();
    }

    private boolean isNull(String parameter) {
        return parameter == null;
    }

    private boolean isGreaterZero(String value) {
        return Long.parseLong(value) > 0;
    }
}