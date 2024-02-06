package com.myblog11.myblog11.exception;

import com.myblog11.myblog11.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)  //used to define a specific exception class will gonna Handled Here
    public ResponseEntity<ErrorDetails> handleIndResourceNotFoundException(
            ResourceNotFoundException r,
            WebRequest webrequest//it give the description of the URI where error occur that's
    ){
        ErrorDetails errorDetails=new ErrorDetails(r.getMessage(),new Date(),webrequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
