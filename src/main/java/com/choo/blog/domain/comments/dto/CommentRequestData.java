package com.choo.blog.domain.comments.dto;

import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestData {
    private String content;
    private boolean secret;

    public Comments createEntity(User author, Post post){
        return Comments.builder()
                .content(content)
                .secret(secret)
                .posts(post)
                .author(author)
                .build();
    }
}
