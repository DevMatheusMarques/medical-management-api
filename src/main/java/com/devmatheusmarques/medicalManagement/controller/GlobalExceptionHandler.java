package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.exception.InactiveUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.devmatheusmarques.medicalManagement.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(InactiveUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(InactiveUserException ex) {
        return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}

