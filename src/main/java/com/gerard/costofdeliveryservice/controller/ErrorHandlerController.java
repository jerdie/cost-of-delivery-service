package com.gerard.costofdeliveryservice.controller;

import com.gerard.costofdeliveryservice.model.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleError(RuntimeException exception){
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }
}
