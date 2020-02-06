package com.workfront.quiz.service.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(id.toString());
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
