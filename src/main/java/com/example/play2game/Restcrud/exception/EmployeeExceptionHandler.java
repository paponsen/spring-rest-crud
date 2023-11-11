package com.example.play2game.Restcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorReponse> handleException(EmployeeNotFoundException e){
        EmployeeErrorReponse errorReponse = new EmployeeErrorReponse();
        errorReponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorReponse.setMessage(e.getMessage());
        errorReponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorReponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorReponse> handleException(Exception e){
        EmployeeErrorReponse errorReponse= new EmployeeErrorReponse();
        errorReponse.setTimeStamp(HttpStatus.BAD_REQUEST.value());
        errorReponse.setMessage(e.getMessage());
        errorReponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorReponse,HttpStatus.BAD_REQUEST);
    }
}
