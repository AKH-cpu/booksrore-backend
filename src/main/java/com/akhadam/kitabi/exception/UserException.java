package com.akhadam.kitabi.exception;

public class UserException extends RuntimeException {

    private static final long serialVersionUID = -5504776961139865758L;

    public UserException(String message) {
        super(message);
    }
}
