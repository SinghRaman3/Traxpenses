package com.fin.Traxpenses.exceptions;

import org.springframework.http.HttpStatus;

public class UserOpsException extends RuntimeException {
    private HttpStatus status;

    public UserOpsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
