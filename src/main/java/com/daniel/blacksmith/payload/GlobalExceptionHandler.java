package com.daniel.blacksmith.payload;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.daniel.blacksmith.exception.BadWordException;
import com.daniel.blacksmith.exception.BlogAPIException;
import com.daniel.blacksmith.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //handle specific Exceptions as well as global Exceptions
    //1. global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false)); //description method : true = whole description, false: only url
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //2. specific Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false)); //description method : true = whole description, false: only url
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false)); //description method : true = whole description, false: only url
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadWordException.class)
    public ResponseEntity<ErrorDetails> handleBadWordException(BadWordException exception,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
            webRequest.getDescription(false)); //description method : true = whole description, false: only url
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
