package com.choo.blog.domain.posts.dto;

import com.choo.blog.domain.posts.Post;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import com.choo.blog.controller.PostController;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PostModel extends EntityModel<Post> {

    public PostModel(Post posts, Link... links) {
        super(posts, (Iterable) Arrays.asList(links));
        add(linkTo(PostController.class).slash(posts.getId()).withSelfRel());
    }
}
