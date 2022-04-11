package com.choo.blog.domain.users.dto;

import com.choo.blog.domain.users.controller.UserController;
import com.choo.blog.domain.users.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UserModel extends EntityModel<User> {
    public UserModel(User user, Link... links) {
        super(user, (Iterable) Arrays.asList(links));
        add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
    }
}
