package com.fin.Traxpenses.exceptions;

import com.fin.Traxpenses.utils.CustomErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(ExpenseOpsException.class)
    public ResponseEntity<?> ExpenseOpsExceptionHandler(ExpenseOpsException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new CustomErrorMessage(ex.getStatus(), ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(UserOpsException.class)
    public ResponseEntity<?> UserOpsExceptionHandler(UserOpsException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new CustomErrorMessage(ex.getStatus(), ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
