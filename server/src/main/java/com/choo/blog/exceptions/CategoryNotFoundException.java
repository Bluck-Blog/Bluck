package com.choo.blog.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id){
        super("category를 찾을 수 없습니다. id : " + id);
    }
}
