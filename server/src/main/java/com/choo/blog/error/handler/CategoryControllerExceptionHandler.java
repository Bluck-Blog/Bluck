package com.choo.blog.error.handler;

import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.DuplicateTitleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryControllerExceptionHandler {
    @ExceptionHandler(value = DuplicateTitleException.class)
    private ResponseEntity<ErrorResponse> handleDuplicatedTitleException(DuplicateTitleException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
