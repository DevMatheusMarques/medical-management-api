package com.devmatheusmarques.medicalManagement.controller;

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
}

