package com.choo.blog.config;

import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRegistData user = UserRegistData.builder()
                .email("test@test.com")
                .password("test")
                .nickname("testNickName")
                .birthdate(LocalDate.of(2022, 04, 11))
                .description("testDescription testest")
                .build();
        userService.join(user);
    }
}
