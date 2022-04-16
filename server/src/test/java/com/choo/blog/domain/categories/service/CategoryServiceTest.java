package com.choo.blog.domain.categories.service;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.security.UserAuthentication;
import com.choo.blog.util.WebTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("카테고리 관리")
class CategoryServiceTest {
    private static final String TITLE = "카테고리 제목";

    private static final String EMAIL = "choo@email.com";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "choo";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1995,11,18);
    private static final String DESCRIPTION = "description";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WebTokenUtil webTokenUtil;
    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp(){
        user = prepareUser("");
        String accessToken = webTokenUtil.encode(user.getId());
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(UserRole.Authorized, accessToken, user.getId()));
    }

    @Nested
    @DisplayName("카테고리 저장은")
    class Descrive_save{
        @Nested
        @DisplayName("카테고리를 입력받으면")
        class Context_with_category{
            CategoryRequestData saveData;

            @BeforeEach
            void setUp(){
                saveData = prepareRequestData("");
            }

            @Test
            @DisplayName("카테고리를 저장하고 저장된 카테고리를 반환한다.")
            void it_return_cateogry(){
                Category category = categoryService.save(saveData);
                assertThat(category.getTitle()).isEqualTo(saveData.getTitle());
                assertThat(category.getUserId()).isEqualTo(user.getId());
            }
        }
    }

    private CategoryRequestData prepareRequestData(String suffix){
        return CategoryRequestData.builder()
                .title(TITLE + "")
                .build();
    }

    private User prepareUser(String suffix){
        return userService.join(prepareUserRegistData(suffix));
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