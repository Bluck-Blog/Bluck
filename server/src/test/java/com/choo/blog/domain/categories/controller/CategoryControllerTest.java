package com.choo.blog.domain.categories.controller;

import com.choo.blog.common.BaseControllerTest;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.SessionResponseData;
import com.choo.blog.domain.users.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
    void cleanUp() throws Exception{
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