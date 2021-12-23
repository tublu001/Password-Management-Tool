package com.epam.rest_controller;

import com.epam.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class WebRequestExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleCityNotFoundException(UserException userException, WebRequest request)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "user");
        response.put("timestamp", new Date().toString());
        response.put("error", userException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}