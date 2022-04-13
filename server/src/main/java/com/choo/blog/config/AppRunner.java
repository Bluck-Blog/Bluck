package com.choo.blog.config;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.util.WebTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {
    private final UserService userService;

    private final WebTokenUtil webTokenUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRegistData user = UserRegistData.builder()
                .email("test@test.com")
                .password("test")
                .nickname("testNickName")
                .birthdate(LocalDate.of(2022, 04, 11))
                .description("testDescription testest")
                .build();
        User joinUser = userService.join(user);
        log.info("accessKey : {}", webTokenUtil.encode(joinUser.getId()));
    }
}
