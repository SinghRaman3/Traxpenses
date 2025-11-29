package com.fin.Traxpenses.utils;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class CustomErrorMessage {
    HttpStatus status;
    String message;
    String timeStamp;
    String path;

    public CustomErrorMessage(HttpStatus status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timeStamp = Instant.now().toString();
        this.path = path;
    }
}