package com.choo.blog.error.handler;

import com.choo.blog.commons.response.ApiResponse;
import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostControllerExceptionHandler {


    @ExceptionHandler(value = PostNotFoundException.class)
    protected ResponseEntity handlePostNotFoundExcption(PostNotFoundException e){
        ErrorResponse error = ErrorResponse.create()
                .message(e.getMessage());
        return ApiResponse.notFound().body(error).toResponse();
    }
}
