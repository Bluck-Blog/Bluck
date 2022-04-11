package com.choo.blog.domain.users.service;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.UserLoginData;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.exceptions.LoginFailException;
import com.choo.blog.exceptions.PasswordNotMatchException;
import com.choo.blog.util.WebTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final WebTokenUtil webTokenUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String login(UserLoginData userLoginData){
        String email = userLoginData.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailException(email));
        if(!passwordEncoder.matches(userLoginData.getPassword(), user.getPassword())){
            throw new PasswordNotMatchException(user.getEmail());
        }
        return webTokenUtil.encode(user.getId());
    }

    public Long parseToken(String accessToken){
        Claims claims = webTokenUtil.decode(accessToken);
        return claims.get("userId", Long.class);
    }
}
