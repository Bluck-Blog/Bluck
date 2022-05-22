package com.choo.blog.domain.categories.exceptions;

public class ForbiddenCategoryException extends RuntimeException {
    public ForbiddenCategoryException(Long id){
        super("카테고리 접근 권한이 없습니다. id : " + id);
    }
}
