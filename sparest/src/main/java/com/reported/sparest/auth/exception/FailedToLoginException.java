package com.reported.sparest.auth.exception;

public class FailedToLoginException extends Exception {

    public FailedToLoginException(String username) {
        super(username);
    }
}
