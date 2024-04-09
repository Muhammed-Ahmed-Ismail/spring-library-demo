package com.library.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,String>> handleCustomExceptions(
            CustomException ex) {
        String error = ex.getMessage();
        String field = ex.getField();
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put(field,error);
        return ResponseEntity.badRequest().body(errorMap);
    }
}
