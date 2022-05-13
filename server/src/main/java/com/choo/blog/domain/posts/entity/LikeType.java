package com.choo.blog.domain.posts.entity;

import com.choo.blog.commons.enums.EnumType;

public enum LikeType implements EnumType {
    LIKE(0), DISLIKE(1);

    public final int id;

    LikeType(int id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String getText() {
        return toString();
    }
}
