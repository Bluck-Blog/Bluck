package com.choo.blog.domain.categories.service;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.categories.exceptions.CategoryNotFoundException;
import com.choo.blog.exceptions.DuplicateTitleException;
import com.choo.blog.domain.categories.exceptions.ForbiddenCategoryException;
import com.choo.blog.exceptions.UserNotFoundException;
import com.choo.blog.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRespository categoryRespository;
    private final UserRepository userRepository;

    public Category save(CategoryRequestData saveData){
        UserAuthentication user = getLoginInfo();

        checkDuplicatedTitle(saveData);

        return categoryRespository.save(saveData.toEntity(user.getUserId()));
    }

    public Category update(Long categoryId, CategoryRequestData updateData){
        Category category = getCategoryById(categoryId);

        chcekModifyPermission(category);

        if(!category.getTitle().equals(updateData.getTitle())){
            checkDuplicatedTitle(updateData);
            category.setTitle(updateData.getTitle());
        }

        return category;
    }

    public Category delete(Long categoryId){
        Category category = getCategoryById(categoryId);

        chcekModifyPermission(category);

        categoryRespository.delete(category);
        return category;
    }

    private void chcekModifyPermission(Category category) {
        if(!category.hasModifyPermission(getLoginUserId())){
            throw new ForbiddenCategoryException(category.getId());
        }
    }

    public List<Category> getCategories(Long userId){
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return categoryRespository.findCategoryByUserId(userId);
    }

    public Category getCategory(Long userId, Long categoryId){
        return categoryRespository.findByUserIdAndId(userId, categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private void checkDuplicatedTitle(CategoryRequestData saveData) {
        if(categoryRespository.existsByUserIdAndAndTitle(getLoginUserId(), saveData.getTitle())){
            throw new DuplicateTitleException(saveData.getTitle());
        }
    }

    private Long getLoginUserId(){
        return getLoginInfo().getUserId();
    }


    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    private Category getCategoryById(Long categoryId) {
        Category category = categoryRespository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return category;
    }
}
