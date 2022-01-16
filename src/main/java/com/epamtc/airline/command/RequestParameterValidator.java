package com.epamtc.airline.command;

import java.util.regex.Pattern;

public class RequestParameterValidator {
    private static final String NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";

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

    private boolean isNull(String parameter) {
        return parameter == null;
    }

    private boolean isGreaterZero(String value) {
        return Long.parseLong(value) > 0;
    }
}