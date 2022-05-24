package com.choo.blog.domain.users;

import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.UserNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("회원 관리")
class UserServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserProperties userProperties;
    @Autowired
    private UserService userService;

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
                assertThat(user.getIntroduction()).isEqualTo(registData.getIntroduction());
                assertThat(user.getNickname()).isEqualTo(registData.getNickname());
                assertThat(passwordEncoder.matches(registData.getPassword(), user.getPassword())).isTrue();
            }
        }

        @Nested
        @DisplayName("회원 조회는")
        class Describe_get{
            @Nested
            @DisplayName("userId를 입력받으면")
            class Context_with_userId{
                User user;

                @BeforeEach
                void setUp(){
                    user = userService.join(userProperties.prepareUserRegistData(""));
                }

                @Test
                @DisplayName("id에 해당하는 user를 반환한다")
                void it_return_user(){
                    User findUser = userService.getUser(this.user.getId());

                    assertThat(findUser).isNotNull();
                    assertThat(findUser.getId()).isNotZero();
                }
            }

            @Nested
            @DisplayName("존재하지 않는 userId를 입력받으면")
            class Context_with_non_exist_userId{
                @Test
                @DisplayName("user를 찾을 수 없다는 예외를 던진다.")
                void it_throw_userNotFoundException(){
                    assertThatThrownBy(() -> userService.getUser(-1L))
                            .isInstanceOf(UserNotFoundException.class);
                }
            }
        }
    }
}