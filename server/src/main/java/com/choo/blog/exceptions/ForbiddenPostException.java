package com.choo.blog.exceptions;

public class ForbiddenPostException extends RuntimeException{
    public ForbiddenPostException(Long id){
        super("게시물 수정권한이 없습니다. 게시물 번호 : " + id);
    }
}
