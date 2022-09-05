package com.choo.blog.domain.categories.dto;

import com.choo.blog.domain.categories.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestData {
    @NotEmpty
    private String title;

    public Category toEntity(Long userId){
        return Category.builder()
                .title(title)
                .userId(userId)
                .build();
    }
}
