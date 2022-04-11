package com.choo.blog.session;

import com.choo.blog.domain.users.UserRole;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    String accessToken() default "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.YtZGmX-aSi2J0hFNj4KT7E8yMid4h1iUSa6-bUODUj4";

    long userId() default 1L;

    UserRole userRole() default UserRole.Authorized;
}
