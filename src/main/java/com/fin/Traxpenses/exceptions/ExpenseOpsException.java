package com.fin.Traxpenses.exceptions;

import org.springframework.http.HttpStatus;

public class ExpenseOpsException extends RuntimeException {
    private HttpStatus status;

    public ExpenseOpsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
