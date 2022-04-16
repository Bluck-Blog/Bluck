package com.choo.blog.domain.categories.repository;

import com.choo.blog.domain.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRespository extends JpaRepository<Category, Long> {
    boolean existsByUserIdAndAndTitle(Long userId, String title);

    List<Category> findCategoryByUserId(Long userId);

    Optional<Category> findByUserIdAndId(Long userId, Long categoryId);
}
