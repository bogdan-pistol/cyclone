package com.ruby.cyclone.configserver.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    protected ResponseEntity<RestError> handleException(RestException ex) {
        return new ResponseEntity<>(new RestError(ex), null, ex.getStatus());
    }
}
