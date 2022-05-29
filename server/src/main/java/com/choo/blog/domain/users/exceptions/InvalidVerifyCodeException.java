package com.choo.blog.domain.users.exceptions;

public class InvalidVerifyCodeException extends RuntimeException{
    public InvalidVerifyCodeException() {
        super("인증번호가 일치하지 않습니다.");
    }
}
