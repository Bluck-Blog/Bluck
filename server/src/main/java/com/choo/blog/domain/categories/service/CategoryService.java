package com.choo.blog.domain.categories.service;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.exceptions.DuplicateTitleException;
import com.choo.blog.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRespository categoryRespository;

    public Category save(CategoryRequestData saveData){
        UserAuthentication user = getLoginInfo();

        if(categoryRespository.existsByUserIdAndAndTitle(user.getUserId(), saveData.getTitle())){
            throw new DuplicateTitleException(saveData.getTitle());
        }

        return categoryRespository.save(saveData.toEntity(user.getUserId()));
    }

    public List<Category> getCategories(Long userId){
        return categoryRespository.findCategoryByUserId(userId);
    }

    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
