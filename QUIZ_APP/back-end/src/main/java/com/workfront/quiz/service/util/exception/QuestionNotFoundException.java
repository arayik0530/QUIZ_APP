package com.workfront.quiz.service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Question")  // 404
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String s) {
        super(s);
    }

    public QuestionNotFoundException(Long id) {
        super(id.toString());
    }

    public QuestionNotFoundException() {
    }
}
