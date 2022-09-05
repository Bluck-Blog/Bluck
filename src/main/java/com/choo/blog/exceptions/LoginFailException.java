package com.choo.blog.exceptions;

import org.springframework.security.core.AuthenticationException;

public class LoginFailException extends AuthenticationException {
    public LoginFailException(String email){
        super("로그인에 실패하였습니다. : " + email);
    }
}
