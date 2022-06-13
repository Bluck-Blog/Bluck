package com.choo.blog.config;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String password = "1234";
        String email = "test@test.com";

        userRepository.save(User.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                .build());
    }
}
