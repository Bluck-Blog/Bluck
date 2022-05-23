package com.choo.blog.domain.users.advice;

import com.choo.blog.domain.users.exceptions.DuplicateEmailException;
import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleUserNotFoundException(UserNotFoundException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return error;
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    protected ErrorResponse handleDuplicateEmailException(DuplicateEmailException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return error;
    }
}
