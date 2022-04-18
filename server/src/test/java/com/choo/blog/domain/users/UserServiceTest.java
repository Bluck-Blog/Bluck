package com.choo.blog.domain.users;

import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("회원 관리")
class UserServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserProperties userProperties;

    @Nested
    @DisplayName("회원 가입은")
    class Descrive_join{
        @Autowired
        UserService userService;

        @Nested
        @DisplayName("회원 정보를 입력받으면")
        class context_with_user_info{
            UserRegistData registData;

            @BeforeEach
            void setUp(){
                registData = userProperties.prepareUserRegistData("");
            }

            @Test
            @DisplayName("회원을 등록하고 등록된 회원 정보를 반환한다.")
            void it_return_user(){
                User user = userService.join(registData);

                assertThat(user.getEmail()).isEqualTo(registData.getEmail());
                assertThat(user.getBirthdate()).isEqualTo(registData.getBirthdate());
                assertThat(user.getDescription()).isEqualTo(registData.getDescription());
                assertThat(user.getNickname()).isEqualTo(registData.getNickname());
                assertThat(passwordEncoder.matches(registData.getPassword(), user.getPassword())).isTrue();
            }
        }
    }
}