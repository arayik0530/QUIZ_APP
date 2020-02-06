package com.workfront.quiz.service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Wrong password")  // 403
public class WrongPasswordException extends RuntimeException { //TODO jshtel vortex kirarel
}
