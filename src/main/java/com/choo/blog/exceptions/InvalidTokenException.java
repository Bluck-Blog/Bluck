package com.choo.blog.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException(String token) {
        super("유효하지 않은 토큰입니다. token: " + token);
    }
}
