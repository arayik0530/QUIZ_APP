package com.workfront.quiz.service.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Topic")  // 404
public class TopicNotFoundException extends RuntimeException {

    public TopicNotFoundException(String s) {
        super(s);
    }

    public TopicNotFoundException(Long id) {
        super(id.toString());
    }
}
