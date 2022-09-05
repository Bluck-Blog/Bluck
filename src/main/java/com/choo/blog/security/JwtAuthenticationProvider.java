package com.choo.blog.security;

import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthentication anonymousAuthentication = (UserAuthentication) authentication;
        String accessToken = anonymousAuthentication.getAccessToken();

        Long userId = authenticationService.parseToken(accessToken);

        return new UserAuthentication(UserRole.Authorized, accessToken, userId);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthentication.class);
    }
}
