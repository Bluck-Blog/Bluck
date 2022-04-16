package com.choo.blog.domain.categories.service;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRespository categoryRespository;

    public Category save(CategoryRequestData saveData){
        UserAuthentication user = getLoginInfo();

        return categoryRespository.save(saveData.toEntity(user.getUserId()));
    }

    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
