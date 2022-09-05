package com.choo.blog.domain.users.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("이미 존재하는 이메일 입니다. eamil : " + email);
    }
}
