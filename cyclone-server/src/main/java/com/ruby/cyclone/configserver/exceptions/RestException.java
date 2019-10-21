package com.ruby.cyclone.configserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestException extends ResponseStatusException {
    public RestException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
