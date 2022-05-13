package com.choo.blog.domain.posts.dto;

import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.security.UserAuthentication;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import com.choo.blog.controller.PostController;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PostModel extends EntityModel<Post> {

    public PostModel(Post posts, Link... links) {
        super(posts, (Iterable) Arrays.asList(links));
        add(linkTo(PostController.class).slash(posts.getId()).withSelfRel());
        Long userId = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId();
        if(posts.hasModifyPermission(userId)) {
            add(linkTo(PostController.class).slash(posts.getId()).withRel("update-url"));
            add(linkTo(PostController.class).slash(posts.getId()).withRel("delete-url"));
        }
    }
}
