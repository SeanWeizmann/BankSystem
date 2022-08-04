package com.sean.bankSystem.exceptions;

public class InvalidException extends Exception{

    public InvalidException() {
        super();
    }

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidException(Throwable cause) {
        super(cause);
    }

    protected InvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
