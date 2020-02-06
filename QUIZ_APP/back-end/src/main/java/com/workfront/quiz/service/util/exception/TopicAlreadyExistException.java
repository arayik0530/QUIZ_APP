package com.workfront.quiz.service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Topic already exists")  // 400
public class TopicAlreadyExistException extends RuntimeException {
}
