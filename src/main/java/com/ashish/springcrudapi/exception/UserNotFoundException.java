package com.ashish.springcrudapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String message;

    public UserNotFoundException(final HttpStatus statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
