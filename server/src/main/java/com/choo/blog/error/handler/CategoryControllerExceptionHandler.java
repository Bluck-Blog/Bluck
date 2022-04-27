package com.choo.blog.error.handler;

import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.CategoryNotFoundException;
import com.choo.blog.exceptions.DuplicateTitleException;
import com.choo.blog.exceptions.ForbiddenCategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Valid;

@RestControllerAdvice
public class CategoryControllerExceptionHandler {
    @ExceptionHandler(value = DuplicateTitleException.class)
    private ResponseEntity<ErrorResponse> handleDuplicatedTitleException(DuplicateTitleException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = ForbiddenCategoryException.class)
    protected ResponseEntity<ErrorResponse> handleForbiddenCategoryException(ForbiddenCategoryException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundCategoryException(CategoryNotFoundException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
