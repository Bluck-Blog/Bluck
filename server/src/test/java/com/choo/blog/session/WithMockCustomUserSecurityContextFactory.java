package com.choo.blog.session;

import com.choo.blog.domain.users.UserRole;
import com.choo.blog.security.UserAuthentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser authentication) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserAuthentication userAuthentication = new UserAuthentication(authentication.userRole(), authentication.accessToken(), authentication.userId());
        context.setAuthentication(userAuthentication);

        return context;
    }
}
