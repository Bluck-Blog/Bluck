package com.choo.blog.exceptions;

import jdk.jshell.ErroneousSnippet;
import lombok.Getter;
import org.springframework.validation.Errors;

public class InvalidParameterException extends RuntimeException{
    @Getter
    private final Errors errors;

    public InvalidParameterException(Errors errors) {
        super("잘못된 입력입니다.");
        this.errors = errors;
    }
}
