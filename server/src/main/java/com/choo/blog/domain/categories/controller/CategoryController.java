package com.choo.blog.domain.categories.controller;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.service.CategoryService;
import com.choo.blog.exceptions.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping(value = "/api/category", produces = MediaTypes.HAL_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class CategoryController{
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createCategory(@RequestBody @Valid CategoryRequestData saveData,
                                         BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }
        Category category = categoryService.save(saveData);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(CategoryController.class).slash(category.getId());
        URI creartedUrl = selfLinkBuilder.toUri();

        return ResponseEntity.created(creartedUrl).body(category);
    }
}
