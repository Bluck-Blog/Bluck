package com.choo.blog.exceptions;

public class DuplicateTitleException extends RuntimeException{
    public DuplicateTitleException(String title){
        super("제목이 중복되었습니다. : " + title);
    }
}
