package com.epam.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.utils.Constants.*;

@ControllerAdvice
public class WebRequestExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException(UserException userException, WebRequest request)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "user");
        response.put("timestamp", new Date().toString());
        response.put("error", userException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("service", "user");
        response.put("timestamp", new Date().toString());
        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.put("error", errorMessage);
        });
        response.put("status", HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public HttpStatus getStatusCode(String exceptionMessage)
    {
        Map<String, HttpStatus> operationObject = new HashMap<>();
        operationObject.put(USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        operationObject.put(DUPLICATE_USER, HttpStatus.NO_CONTENT);

        return operationObject.get(exceptionMessage);
    }
}