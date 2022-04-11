package com.choo.blog.exceptions;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotMatchException extends AuthenticationException {
    public PasswordNotMatchException(String email){
        super("비밀번호가 일치하지 않습니다. email : " + email);
    }
}
