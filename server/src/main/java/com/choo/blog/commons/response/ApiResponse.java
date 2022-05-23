package com.choo.blog.commons.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    private int result;
    private T data;
    private HttpStatus status;

    @Builder
    protected ApiResponse(int result, T data, HttpStatus status) {
        this.result = result;
        this.data = data;
        this.status = status;
    }
}
