package com.choo.blog.domain.users;

import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 관리")
class UserControllerTest {
    private static final String EMAIL = "choo@email.com";
    private static final String PASSWORD = "choo@1234";
    private static final String NICKNAME = "choo";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1995,11,18);
    private static final String DESCRIPTION = "description";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

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
            registData = prepareUserRegistData("");
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
                        .andExpect(jsonPath("description").value(registData.getDescription()))
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
                ReflectionTestUtils.setField(registData, "description", "");
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