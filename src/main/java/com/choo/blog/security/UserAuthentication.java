package com.choo.blog.security;

import com.choo.blog.domain.users.UserRole;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserAuthentication extends AbstractAuthenticationToken {
    private String accessToken;

    private Long userId;

    private UserRole role;

    public UserAuthentication(UserRole role, String accessToekn, Long userId) {
        super(authorities(role));
        this.accessToken = accessToekn;
        this.userId = userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    private static List<GrantedAuthority> authorities(UserRole role) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
