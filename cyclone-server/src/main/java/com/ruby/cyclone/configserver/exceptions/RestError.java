package com.ruby.cyclone.configserver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class RestError {

    private final int code;
    private final String message;

    RestError(RestException ex) {
        this.code = ex.getStatus().value();
        this.message = ex.getReason();
    }
}
