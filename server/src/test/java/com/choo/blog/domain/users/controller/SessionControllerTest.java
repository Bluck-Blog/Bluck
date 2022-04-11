package com.choo.blog.domain.users.controller;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.SessionResponseData;
import com.choo.blog.domain.users.dto.UserLoginData;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.util.WebTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("세션 관리")
class SessionControllerTest {
    private static final String EMAIL = "choo@email.com";
    private static final String PASSWORD = "choo@1234";
    private static final String NICKNAME = "choo";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1995,11,18);
    private static final String DESCRIPTION = "description";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebTokenUtil webTokenUtil;

    @Nested
    @DisplayName("로그인은")
    class Describe_login{
        User user;

        @BeforeEach
        void setUp() throws Exception {
            user = prepareUser("");
        }

        @Nested
        @DisplayName("유효한 로그인 정보가 주어지면")
        class Context_with_loginData{
            UserLoginData loginData;

            @BeforeEach
            void setUp(){
                loginData = UserLoginData.builder()
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
            }

            @Test
            @DisplayName("토큰을 반환한다.")
            void it_return_token() throws Exception {
                mockMvc.perform(post("/session")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(loginData)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.accessToken").exists());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 email이 주어지면")
        class Context_with_non_exist_email{
            UserLoginData invalidEmailLoginData;

            @BeforeEach
            void setUp(){
                invalidEmailLoginData = UserLoginData.builder()
                        .email(EMAIL + "wrong")
                        .password(PASSWORD)
                        .build();
            }
            @Test
            @DisplayName("401에러를 반환한")
            void it_return_unAuthorized() throws Exception{
                mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmailLoginData)))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("잘못된 비밀번호가 주어지면")
        class Context_with_wrong_password{
            UserLoginData wrongPasswordLoginData;

            @BeforeEach
            void setUp(){
                wrongPasswordLoginData = UserLoginData.builder()
                        .email(EMAIL)
                        .password(PASSWORD + "wrong")
                        .build();
            }
            @Test
            @DisplayName("401 에러를 반환한다.")
            void it_return_unAuthorized() throws Exception{
                mockMvc.perform(post("/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(wrongPasswordLoginData)))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("빈 데이터가 주어지면")
        class Context_with_empty_request{
            @Test
            @DisplayName("400에러를 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
            }
        }
    }

    private User prepareUser(String suffix) throws Exception{
        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(prepareUserRegistData(suffix)))).andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        return objectMapper.readValue(content, User.class);
    }

    public UserRegistData prepareUserRegistData(String suffix){
        return UserRegistData.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .birthdate(BIRTH_DATE)
                .description(DESCRIPTION)
                .build();
    }
}