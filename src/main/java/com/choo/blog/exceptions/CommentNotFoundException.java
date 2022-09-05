package com.choo.blog.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(Long commentId){
        super("댓글이 존재하지 않습니다. : " + commentId);
    }
}
