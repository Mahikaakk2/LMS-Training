package com.lms.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QueryAlreadyResolvedException extends RuntimeException {
    public QueryAlreadyResolvedException(String message){
        super(message);
    }
}
