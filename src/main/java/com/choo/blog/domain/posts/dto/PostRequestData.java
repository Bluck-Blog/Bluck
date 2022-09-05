package com.choo.blog.domain.posts.dto;

import com.choo.blog.domain.posts.enums.PostOpenType;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestData {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    @NotNull
    private Long categoryId;

    private PostOpenType openType;

    public Post createEntity(User author){
        return Post.builder()
                .title(title)
                .content(content)
                .view(0)
                .openType(openType)
                .author(author)
                .build();
    }
}
