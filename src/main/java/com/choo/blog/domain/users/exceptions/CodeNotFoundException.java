package com.choo.blog.domain.users.exceptions;

public class CodeNotFoundException extends RuntimeException {
    public CodeNotFoundException() {
        super("인증번호가 존재하지 않습니다.");
    }
}
