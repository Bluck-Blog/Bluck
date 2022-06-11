package com.choo.blog.domain.users.controller;

import com.choo.blog.common.BaseControllerTest;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.UserLoginData;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.util.WebTokenUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mockStatic;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("세션 관리")
class SessionControllerTest extends BaseControllerTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WebTokenUtil webTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("로그인은")
    class Describe_login{
        User user;

        @BeforeEach
        void setUp() throws Exception {
            commentRepository.deleteAll();
            postRepository.deleteAll();
            categoryRespository.deleteAll();
            userRepository.deleteAll();

            user = prepareUser("");
        }

        @AfterEach
        void cleanUp() throws Exception {
            userRepository.deleteAll();;
        }

        @Nested
        @DisplayName("유효한 로그인 정보가 주어지면")
        class Context_with_loginData{
            UserLoginData loginData;

            @BeforeEach
            void setUp(){
                loginData = UserLoginData.builder()
                        .email(userProperties.getEmail())
                        .password(userProperties.getPassword())
                        .build();
            }

            @Test
            @DisplayName("토큰을 반환한다.")
            void it_return_token() throws Exception {
                mockMvc.perform(post("/api/session")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(loginData)))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.accessToken").exists())
                        .andDo(document(
                                "login",
                                requestFields(
                                    fieldWithPath("email").type(JsonFieldType.STRING).description("로그인 이메일"),
                                    fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 패스워드")
                                ),
                                responseFields(
                                        fieldWithPath("accessToken").type(JsonFieldType.STRING).description("로그인 토큰")
                                )
                        ));
            }
        }

        @Nested
        @DisplayName("존재하지 않는 email이 주어지면")
        class Context_with_non_exist_email{
            UserLoginData invalidEmailLoginData;

            @BeforeEach
            void setUp(){
                invalidEmailLoginData = UserLoginData.builder()
                        .email(userProperties.getEmail() + "wrong")
                        .password(userProperties.getPassword())
                        .build();
            }
            @Test
            @DisplayName("401에러를 반환한")
            void it_return_unAuthorized() throws Exception{
                mockMvc.perform(post("/api/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmailLoginData)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.UNAUTHORIZED.name()))
                        .andExpect(jsonPath("code").isNumber())
                        .andExpect(jsonPath("body.message").exists())
                ;
            }
        }

        @Nested
        @DisplayName("잘못된 비밀번호가 주어지면")
        class Context_with_wrong_password{
            UserLoginData wrongPasswordLoginData;

            @BeforeEach
            void setUp(){
                wrongPasswordLoginData = UserLoginData.builder()
                        .email(userProperties.getEmail())
                        .password(userProperties.getPassword() + "wrong")
                        .build();
            }
            @Test
            @DisplayName("401 에러를 반환한다.")
            void it_return_unAuthorized() throws Exception{
                mockMvc.perform(post("/api/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(wrongPasswordLoginData)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.UNAUTHORIZED.name()))
                        .andExpect(jsonPath("code").isNumber())
                        .andExpect(jsonPath("body.message").exists());
            }
        }

        @Nested
        @DisplayName("빈 데이터가 주어지면")
        class Context_with_empty_request{
            @Test
            @DisplayName("400에러를 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/api/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
            }
        }
    }

    @Nested
    @DisplayName("이메일 인증 요청은")
    class Describe_request_verify_email{
        @Nested
        @DisplayName("이메일이 주어지면")
        class Context_with_email {
            String email;

            @Test
            @DisplayName("이메일 인증번호를 반환한다.")
            void it_return_verifyCode() throws Exception {
                mockMvc.perform(get("/api/session/verify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .param("email", email)
                        )
                        .andDo(print())
                        .andExpect(jsonPath("status").value(HttpStatus.CREATED.name()));
            }
        }
    }

    @Nested
    @DisplayName("이메일 인증은")
    class Describe_verify_email{
        MockHttpSession mockHttpSession;

        String rawCode;

        @BeforeEach
        void setUp(){
            rawCode = "A1234";
            mockHttpSession = userProperties.generateVerifySession(passwordEncoder, rawCode);

            mockHttpSession.setAttribute("code", rawCode);
        }

        @AfterEach
        void cleanUp(){
            mockHttpSession.clearAttributes();
        }

        @Nested
        @DisplayName("올바른 인증번호가 주어지면")
        class Context_with_valid_verify_code{
            @Test
            @DisplayName("Http 200 을 반환한다.")
            void it_return_status_ok() throws Exception{
                mockMvc.perform(post("/api/session/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(mockHttpSession)
                        .accept(MediaTypes.HAL_JSON)
                        .param("code", rawCode)
                )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body").value("true"))
                        .andExpect(jsonPath("status").value(HttpStatus.OK.name()));
            }
        }

        @Nested
        @DisplayName("유효하지 않은 인증번호가 주어지면")
        class Context_with_invalid_verify_code{
            @Test
            @DisplayName("Http 200 을 반환한다.")
            void it_return_status_ok() throws Exception{
                mockMvc.perform(post("/api/session/verify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .session(mockHttpSession)
                                .accept(MediaTypes.HAL_JSON)
                                .param("code", "wrongCode")
                        )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body").value("false"))
                        .andExpect(jsonPath("status").value(HttpStatus.OK.name()));
            }
        }
    }
}