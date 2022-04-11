package com.choo.blog.filter;

import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.service.AuthenticationService;
import com.choo.blog.security.UserAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends BasicAuthenticationFilter {
    private final AuthenticationService authenticationService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToekn = parseAuthorizationHeaderFrom(request);

        if(!accessToekn.isEmpty()){
            Long userId = authenticationService.parseToken(accessToekn);
            Authentication authResult = this.getAuthenticationManager().authenticate(
                    new UserAuthentication(UserRole.Authorized, accessToekn, userId)
            );
            onSuccessfulAuthentication(request, response, authResult);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        authResult.setAuthenticated(true);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authResult);
    }

    private String parseAuthorizationHeaderFrom(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        if(authorization == null){
            return "";
        }
        return authorization.substring("Bearer ".length());
    }
}
