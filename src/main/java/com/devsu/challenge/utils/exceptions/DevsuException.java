package com.devsu.challenge.utils.exceptions;

public class DevsuException extends Exception{

    public DevsuException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevsuException(String message) {
        super(message);
    }
}
