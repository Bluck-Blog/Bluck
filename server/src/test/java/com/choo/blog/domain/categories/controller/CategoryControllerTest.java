package com.choo.blog.domain.categories.controller;

import com.choo.blog.common.BaseControllerTest;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("카테고리 괸리")
class CategoryControllerTest extends BaseControllerTest {
    private final static String TITLE = "카테고리 제목";

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryRespository categoryRespository;

    @BeforeEach
    void setUp() throws Exception{
        user = prepareUser("");
        session = login(prepareLoginData());
    }

    @AfterEach
    void cleanUp() {
        categoryRespository.deleteAll();
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("카테고리 생성은")
    class Descrive_save{
        CategoryRequestData saveData;

        @BeforeEach
        void setUp(){
            saveData = prepareRquestData("");
        }

        @Nested
        @DisplayName("카테고리 제목을 입력받으면")
        class Context_with_category {

            @Test
            @DisplayName("카테고리를 생성하고 생성된 카테고리를 반환한다.")
            void it_return_new_category() throws Exception{
                System.out.println(saveData.getTitle());
                mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(saveData))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                )
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("id").exists())
                        .andExpect(jsonPath("title").value(saveData.getTitle()))
                        .andExpect(jsonPath("userId").value(user.getId()));

            }
        }

        @Nested
        @DisplayName("중복된 제목을 입력받으면")
        class Context_with_duplicated_title{
            @BeforeEach
            void setUp(){
                prepareCategory("");
            }

            @Test
            @DisplayName("badRequest를 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(saveData))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                )
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("message").exists())
                        ;
            }
        }

        @Nested
        @DisplayName("인증정보가 없으면")
        class Context_with_non_accessToken{
            @Test
            @DisplayName("unAuhtorize 상태코드를 반환한다.")
            void it_return_status_unAuthorize() throws Exception{
                mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(saveData))
                )
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("입력값이 비어 있다면")
        class Context_ith_empty_input{
            @Test
            @DisplayName("badRequest 상태 코드를 반환한다.")
            void it_return_status_badRequest() throws Exception{
                mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                )
                        .andDo(print())
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("title이 비어있으면")
        class Context_with_empty_title{
            @BeforeEach
            void setUp(){
                ReflectionTestUtils.setField(saveData, "title", "", String.class);
            }

            @Test
            @DisplayName("badRequest 상태 코드를 반환한다.")
            void it_return_status_badRequest() throws Exception{
                mockMvc.perform(post("/api/category")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(saveData))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("errors[0].field").value("title"))
                ;
            }
        }
    }

    @Nested
    @DisplayName("카테고리 수정은")
    class Descrive_update{
        Category category;
        CategoryRequestData updateData;


        @BeforeEach
        void setUp(){
            category = prepareCategory("");
            updateData = prepareRquestData("_NEW");
        }

        @Nested
        @DisplayName("카테고리 제목과 카테고리 id를 입력받으면")
        class Context_with_categoryId_and_title{

            @Test
            @DisplayName("카테고리를 수정하고 수정된 카테고리를 반환한다.")
            void it_return_category() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", category.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(updateData))
                            .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken())
                        )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("id").exists())
                        .andExpect(jsonPath("title").value(updateData.getTitle()));
            }
        }

        @Nested
        @DisplayName("인증정보가 없으면")
        class Context_with_non_accessToken{
            @Test
            @DisplayName("unAuhtorize 상태코드를 반환한다.")
            void it_return_status_unAuthorize() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", category.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                        )
                            .andDo(print())
                            .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 categoryId가 주어지면")
        class Context_with_non_exist_categoryId{
            @Test
            @DisplayName("404 상태코드를 반환한다.")
            void it_return_status_notFound() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", -1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(updateData))
                        .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken())
                )
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("message").exists());
            }
        }

        @Nested
        @DisplayName("title이 비어있으면")
        class Context_with_empty_title{
            @BeforeEach
            void setUp(){
                ReflectionTestUtils.setField(updateData, "title", "", String.class);
            }

            @Test
            @DisplayName("badRequest 상태 코드를 반환한다.")
            void it_return_status_badRequest() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", category.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("message").exists())
                        .andExpect(jsonPath("errors[0].field").value("title"))
                ;
            }
        }

        @Nested
        @DisplayName("중복된 제목을 입력받으면")
        class Context_with_duplicated_title{
            @BeforeEach
            void setUp(){
                prepareCategory("_NEW");
            }

            @Test
            @DisplayName("badRequest를 반환한다.")
            void it_return_badRequest() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", category.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + session.getAccessToken())
                        )
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("message").exists())
                ;
            }
        }

        @Nested
        @DisplayName("카테고리 생성자와 다른 인증정보가 주어진다면")
        class Context_with_differnt_autentication{
            String accessToken;

            @BeforeEach
            void setUp() throws Exception{
                User other = prepareUser("other");
                accessToken = webTokenUtil.encode(other.getId());
            }

            @Test
            @DisplayName("403 에러코드를 반환한다,")
            void it_return_status_unAuhorize() throws Exception{
                mockMvc.perform(patch("/api/category/{id}", category.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        )
                        .andDo(print())
                        .andExpect(status().isUnauthorized())
                        .andExpect(jsonPath("message").exists());
            }
        }
    }

    private CategoryRequestData prepareRquestData(String suffix){
        return CategoryRequestData.builder()
                .title(TITLE + suffix)
                .build();
    }

    private Category prepareCategory(String suffix){
        CategoryRequestData requestData = prepareRquestData(suffix);
        return categoryRespository.save(requestData.toEntity(user.getId()));
    }
}