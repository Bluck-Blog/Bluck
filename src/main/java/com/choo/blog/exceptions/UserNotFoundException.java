package com.choo.blog.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("유저를 찾을 수 없습니다. id : " + userId);
    }
}
