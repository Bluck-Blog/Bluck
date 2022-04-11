package com.choo.blog.domain.posts.dto;

import com.choo.blog.domain.posts.PostOpenType;
import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestData {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    private PostOpenType openType;

    public Post createEntity(User author){
        return Post.builder()
                .title(title)
                .content(content)
                .likes(0)
                .dislikes(0)
                .view(0)
                .openType(openType)
                .author(author)
                .build();
    }
}
