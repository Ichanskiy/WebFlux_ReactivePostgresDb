package com.reactive.education.controllers;

import com.reactive.education.exeptions.DuplicateEmailException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController extends ResponseStatusExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<CustomException> handleThereIsNoSuchDepartmentException() {
        return new ResponseEntity<>(new CustomException("Object is not exist"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<CustomException> handleDuplicateEmailException() {
        return new ResponseEntity<>(new CustomException("Email is duplicate"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class CustomException {
        private String message;
    }
}
