package com.epamtc.airline.dao.exception;

public class ConnectionPoolException extends RuntimeException{
    private static final long serialVersionUID = 2550322384818833199L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
