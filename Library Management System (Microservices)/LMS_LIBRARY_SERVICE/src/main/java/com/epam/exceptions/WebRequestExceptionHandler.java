package com.epam.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleAnyException(ConnectException exception, WebRequest request)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "user");
        response.put("timestamp", new Date().toString());
        response.put("error", "Couldn't communicate with server!!!");
        response.put("status", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleAnyHttpClientException(HttpClientErrorException exception, WebRequest request) throws JsonProcessingException
    {
        JSONObject jsonObj = new JSONObject();
        ErrorApi errorApi = new ObjectMapper().readValue(exception.getResponseBodyAsString(), ErrorApi.class);
//        Map<String, Object> response = new LinkedHashMap<>();
//        response.put("service", "user");
//        response.put("timestamp", new Date().toString());
//        response.put("error", exception.getMessage());
//        response.put("status", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errorApi, HttpStatus.NOT_FOUND);
    }
}