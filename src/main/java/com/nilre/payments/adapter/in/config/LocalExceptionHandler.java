package com.nilre.payments.adapter.in.config;

import com.nilre.payments.application.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class LocalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
