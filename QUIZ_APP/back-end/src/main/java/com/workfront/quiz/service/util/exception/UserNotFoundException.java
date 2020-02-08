package com.workfront.quiz.service.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User "+id + " not found.");
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
