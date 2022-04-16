package com.choo.blog.domain.categories.repository;

import com.choo.blog.domain.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category, Long> {
    boolean existsByUserIdAndAndTitle(Long userId, String title);
}
