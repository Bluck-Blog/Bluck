package com.choo.blog.domain.users.handler;

import com.choo.blog.commons.response.ApiResponse;
import com.choo.blog.domain.users.exceptions.DuplicateEmailException;
import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity handleUserNotFoundException(UserNotFoundException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ApiResponse.notFound().body(error).toResponse();
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    protected ResponseEntity handleDuplicateEmailException(DuplicateEmailException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ApiResponse.status(HttpStatus.BAD_REQUEST).body(error).toResponse();
    }
}
