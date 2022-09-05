package com.choo.blog.domain.posts.enums;

import com.choo.blog.commons.enums.EnumType;

public enum PostOpenType implements EnumType {
    ALL(0), FRIEND(1), SPECIFIC(2);

    public final int id;

    PostOpenType(int id) {
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
