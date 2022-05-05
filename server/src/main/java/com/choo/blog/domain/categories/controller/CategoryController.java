package com.choo.blog.domain.categories.controller;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryModel;
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
import java.util.List;
import java.util.stream.Collectors;

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

        CategoryModel categoryModel = new CategoryModel(category);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(CategoryController.class).slash(category.getId());
        URI creartedUrl = selfLinkBuilder.toUri();

        return ResponseEntity.created(creartedUrl).body(categoryModel);
    }

    @PatchMapping("/{categoryId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateCategory(@PathVariable Long categoryId,
                                         @RequestBody @Valid CategoryRequestData updateData,
                                         BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }
        Category category = categoryService.update(categoryId, updateData);

        CategoryModel categoryModel = new CategoryModel(category);

        return ResponseEntity.ok(categoryModel);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId){
        categoryService.delete(categoryId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getCategories(@PathVariable Long userId){
        List<Category> categories = categoryService.getCategories(userId);

        //TODO : RepresentationModelAssembler 활용
        List<CategoryModel> categoryModels = categories
                .stream()
                .map(CategoryModel::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categoryModels);
    }
}
