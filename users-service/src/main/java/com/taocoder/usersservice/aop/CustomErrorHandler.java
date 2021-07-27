/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taocoder.usersservice.aop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author user
 */

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Error: " + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}