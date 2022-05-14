package com.choo.blog.domain.comments.exceptions;

public class ForbiddenCommentException extends RuntimeException{
    public ForbiddenCommentException(Long categoryId){
        super("카테고리 접근 권힌이 없습니다. categoryId : " + categoryId);
    }
}
