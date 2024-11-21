package com.api_franchises.api_franchises.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BranchException extends HandlerException {
    public BranchException(String message, Integer code, HttpStatus status, LocalDateTime timestamp) {
        super(message, code, status, timestamp);
    }
}
