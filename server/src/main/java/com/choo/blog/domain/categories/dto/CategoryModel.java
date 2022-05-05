package com.choo.blog.domain.categories.dto;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.controller.CategoryController;
import com.choo.blog.security.UserAuthentication;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class CategoryModel extends EntityModel<Category> {
    public CategoryModel(Category category, Link... links){
        super(category, (Iterable) Arrays.asList(links));
        add(linkTo(CategoryController.class).slash(category.getId()).withSelfRel());
        if(SecurityContextHolder.getContext().getAuthentication() instanceof UserAuthentication) {
            Long userId = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId();

            if (category.hasModifyPermission(userId)) {
                add(linkTo(CategoryController.class).slash(category.getId()).withRel("update-url"));
                add(linkTo(CategoryController.class).slash(category.getId()).withRel("delete-url"));
            }
        }
    }
}
