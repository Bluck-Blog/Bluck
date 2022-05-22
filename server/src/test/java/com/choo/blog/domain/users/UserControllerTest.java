package com.choo.blog.domain.users;

import com.choo.blog.common.BaseControllerTest;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 관리")
class UserControllerTest extends BaseControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    void setUp(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
        categoryRespository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("회원 가입은")
    class Describe_regist{
        UserRegistData registData;

        @BeforeEach
        void setUp(){
            registData = userProperties.prepareUserRegistData("");
        }

        @Nested
        @DisplayName("회원 정보를 입력받으면")
        class context_with_user_info{
            @Test
            @DisplayName("status 201 created를 반환한다.")
            void it_return_created() throws Exception {
                mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(registData)))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("email").value(registData.getEmail()))
                        .andExpect(jsonPath("nickname").value(registData.getNickname()))
                        .andExpect(jsonPath("introduction").value(registData.getIntroduction()))
                        .andExpect(jsonPath("password").doesNotExist())
                        .andExpect(jsonPath("_links.self").exists());
            }
        }

        @Nested
        @DisplayName("잘못된 정보를 입력하면")
        class context_with_wrong_info{
            @BeforeEach
            void setUp(){
                ReflectionTestUtils.setField(registData, "email", "");
                ReflectionTestUtils.setField(registData, "password", "");
                ReflectionTestUtils.setField(registData, "nickname", "");
                ReflectionTestUtils.setField(registData, "introduction", "");
            }
            @Test
            @DisplayName("에러 코드 400을 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(registData)))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("errors[0].objectName").exists())
                        .andExpect(jsonPath("errors[0].code").exists())
                        .andExpect(jsonPath("errors[0].rejectedValue").hasJsonPath());
            }
        }

        @Nested
        @DisplayName("이미 존재하는 email을 입력하면")
        class context_with_exsist_email{
            @BeforeEach
            public void setUp() throws Exception {
                prepareUser("");
            }
            @Test
            @DisplayName("에러 코드 400을 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(registData)))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("errors[0].objectName").exists())
                        .andExpect(jsonPath("errors[0].code").exists())
                        .andExpect(jsonPath("errors[0].rejectedValue").hasJsonPath());
            }
        }
    }
}