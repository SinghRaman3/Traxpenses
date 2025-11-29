package com.fin.Traxpenses.exceptions;

import com.fin.Traxpenses.utils.CustomErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(ExpenseOpsException.class)
    public ResponseEntity<?> ExpenseOpsExceptionHandler(ExpenseOpsException ex, HttpServletRequest request) {
        return new ResponseEntity<>(new CustomErrorMessage(ex.getStatus(), ex.getMessage(), request.getRequestURI()), ex.getStatus());
    }

    @ExceptionHandler(UserOpsException.class)
    public ResponseEntity<?> UserOpsExceptionHandler(UserOpsException ex, HttpServletRequest request) {
        return new ResponseEntity<>(new CustomErrorMessage(ex.getStatus(), ex.getMessage(), request.getRequestURI()), ex.getStatus());
    }
}
