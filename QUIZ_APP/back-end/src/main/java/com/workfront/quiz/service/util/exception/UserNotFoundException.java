package com.workfront.quiz.service.util.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(id.toString());
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
