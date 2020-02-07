package com.workfront.quiz.service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Question already exists")  // 400
public class QuestionAlreadyExistException extends RuntimeException {

    public QuestionAlreadyExistException(String s) {
        super(s);
    }
}
