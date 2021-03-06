package com.choo.blog.domain.users.handler;

import com.choo.blog.commons.response.ApiResponse;
import com.choo.blog.domain.users.exceptions.CodeNotFoundException;
import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.LoginFailException;
import com.choo.blog.exceptions.PasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SessionControllerExceptionHandler {
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity handleLoginFailException(LoginFailException ex){
        ErrorResponse error = ErrorResponse.create()
                .message(ex.getMessage());
        return ApiResponse.status(HttpStatus.UNAUTHORIZED, -3).body(error).toResponse();
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity handlePasswordNotMatchExdception(PasswordNotMatchException ex) {
        ErrorResponse error = ErrorResponse.create()
                .message(ex.getMessage());
        return ApiResponse.status(HttpStatus.UNAUTHORIZED, -2)
                .body(error)
                .toResponse();
    }

    @ExceptionHandler(CodeNotFoundException.class)
    public ResponseEntity handleCodeNotFoundException(CodeNotFoundException ex) {
        ErrorResponse error = ErrorResponse.create()
                .message(ex.getMessage());
        return ApiResponse.status(HttpStatus.BAD_REQUEST).body(error).toResponse();
    }
}
