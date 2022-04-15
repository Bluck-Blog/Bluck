package com.choo.blog.error.handler;

import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.InvalidParameterException;
import com.choo.blog.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonControllerExceptionHandler {
    @ExceptionHandler(value = InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage())
                .errors(e.getErrors());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
