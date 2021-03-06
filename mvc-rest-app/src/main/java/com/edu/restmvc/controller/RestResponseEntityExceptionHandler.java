package com.edu.restmvc.controller;

import com.edu.restmvc.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest webRequest) {
        ResponseEntity<Object> responseEntity = ResponseEntity.<Object>status(HttpStatus.NOT_FOUND)
                .headers(new HttpHeaders())
                .body("Resource not found");
        return responseEntity;
    }


}
