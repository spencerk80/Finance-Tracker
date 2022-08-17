package com.GenSpark.Finance.Tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
