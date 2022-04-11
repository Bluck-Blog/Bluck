package com.choo.blog.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id) {
        super("해당 게시글이 존재하지 않습니다. 게시물 번호 : " + id);
    }
}
