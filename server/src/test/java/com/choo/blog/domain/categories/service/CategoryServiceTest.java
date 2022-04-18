package com.choo.blog.domain.categories.service;

import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.CategoryNotFoundException;
import com.choo.blog.exceptions.DuplicateTitleException;
import com.choo.blog.exceptions.ForbiddenCategoryException;
import com.choo.blog.security.UserAuthentication;
import com.choo.blog.util.WebTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("카테고리 관리")
class CategoryServiceTest {
    private static final String TITLE = "카테고리 제목";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRespository categoryRespository;
    @Autowired
    private WebTokenUtil webTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserProperties userProperties;

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

        @Nested
        @DisplayName("중복된 이름을 입력받으면")
        class Context_with_dupliacted_title{
            CategoryRequestData saveData;

            @BeforeEach
            void setUp(){
                saveData = prepareRequestData("");
                categoryRespository.save(saveData.toEntity(user.getId()));
            }

            @Test
            @DisplayName("이름이 중복되었다는 예외를 던진다")
            void it_throw_duplicatedTitleException(){
                assertThatThrownBy(() -> categoryService.save(saveData))
                        .isInstanceOf(DuplicateTitleException.class);
            }
        }
    }

    @Nested
    @DisplayName("카테고리 목록 조회는")
    class Descrive_findAll{
        @Nested
        @DisplayName("유저 pk를 입력받으면")
        class Context_with_userId{
            int size = 30;

            @BeforeEach
            void setUp(){
                IntStream.range(0, size).forEach(i ->{
                    categoryRespository.save(prepareRequestData(i + "").toEntity(user.getId()));
                });
            }

            @Test
            @DisplayName("해당 유저의 카테고리 목록을 반환한다.")
            void it_return_category_list_of_user(){
                List<Category> categories = categoryService.getCategories(user.getId());
                assertThat(categories.size()).isEqualTo(size);
            }
        }
    }

    @Nested
    @DisplayName("카테고리 조회는")
    class Descrive_find{
        @Nested
        @DisplayName("userId 와 categoryId를 입력받으면")
        class Context_with_userId_and_categoryId{
            Category category;

            @BeforeEach
            void setUp(){
                category = categoryRespository.save(prepareRequestData("").toEntity(user.getId()));
            }

            @Test
            @DisplayName("해당 카테고리를 반환한다.")
            void it_return_category(){
                Category findCategory = categoryService.getCategory(user.getId(), this.category.getId());

                assertThat(findCategory.getId()).isEqualTo(category.getId());
                assertThat(findCategory.getTitle()).isEqualTo(category.getTitle());
                assertThat(findCategory.getUserId()).isEqualTo(user.getId());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 categoryId가 주어진다면")
        class Context_with_non_exist_categoryId{
            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_cateoryNotFoundException(){
                assertThatThrownBy(() -> categoryService.getCategory(user.getId(), -1L))
                        .isInstanceOf(CategoryNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("카테고리의 userId와 다른 userId가 주어진다면")
        class Context_with_differnt_userId{
            Category category;
            @BeforeEach
            void setUp(){
                User otherUser = prepareUser("other");
                category = categoryRespository.save(prepareRequestData("").toEntity(otherUser.getId()));
            }
            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundException(){
                assertThatThrownBy(() -> categoryService.getCategory(-1L, category.getId()))
                        .isInstanceOf(CategoryNotFoundException.class);
            }
        }
    }

    @Nested
    @DisplayName("카테고리 수정은")
    class Descrive_update{
        CategoryRequestData updateData;

        @BeforeEach
        void setUp(){
            updateData = prepareRequestData("_NEW");
        }

        @Nested
        @DisplayName("수정 데이터를 입력 받으면")
        class Context_with_update_data{
            Category category;

            @BeforeEach
            void setUp(){
                category = categoryRespository.save(prepareRequestData("").toEntity(user.getId()));
            }

            @Test
            @DisplayName("카테고리를 수정하고 수정된 카테고리를 반환한다.")
            void it_return_category(){
                Category updatedCategory = categoryService.update(category.getId(), updateData);

                assertThat(updatedCategory.getTitle()).isEqualTo(updateData.getTitle());
                assertThat(updatedCategory.getId()).isEqualTo(category.getId());
                assertThat(updatedCategory.getUserId()).isEqualTo(category.getUserId());
            }
        }

        @Nested
        @DisplayName("존재 하지 않는 카테고리의 아이디가 주어지면")
        class Context_woth_non_exist_categoryId{

            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundException(){
                assertThatThrownBy(() -> categoryService.update(-1L, updateData))
                        .isInstanceOf(CategoryNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("중복된 이름을 입력받으면")
        class Context_with_dupliacted_title{
            Category category;
            @BeforeEach
            void setUp(){
                category = categoryRespository.save(prepareRequestData("").toEntity(user.getId()));
                categoryRespository.save(updateData.toEntity(user.getId()));
            }

            @Test
            @DisplayName("이름이 중복되었다는 예외를 던진다")
            void it_throw_duplicatedTitleException(){
                assertThatThrownBy(() -> categoryService.update(category.getId(), updateData))
                        .isInstanceOf(DuplicateTitleException.class);
            }
        }

        @Nested
        @DisplayName("카테고리 생성자와 다른 인증정보가 주어진다면")
        class Context_with_diffrent_authentication{
            Category category;

            @BeforeEach
            void setUp(){
                User otherUser = prepareUser("other");
                category = categoryRespository.save(prepareRequestData("").toEntity(otherUser.getId()));
            }

            @Test
            @DisplayName("카테고리 접근권한이 없다는 예외를 던진다.")
            void it_throw_forbiddenCategoryException(){
                assertThatThrownBy(() -> categoryService.update(category.getId(), updateData))
                        .isInstanceOf(ForbiddenCategoryException.class);
            }
        }
    }

    @Nested
    @DisplayName("카테고리 삭제는")
    class Descrive_delete{
        @Nested
        @DisplayName("userId 와 categoryId가 주어지면")
        class Context_with_userId_and_categoryId{
            Category category;

            @BeforeEach
            void setUp(){
                category = categoryRespository.save(prepareRequestData("").toEntity(user.getId()));
            }
            @Test
            @DisplayName("카테고리를 삭제하고 삭제된 카테고리를 반환한다.")
            void it_return_category(){
                categoryService.delete(category.getId());

                assertThat(categoryRespository.findById(category.getId()).isPresent()).isFalse();
            }
        }

        @Nested
        @DisplayName("존재하지 않는 categoryId가 주어진다면")
        class Context_with_non_exist_categoryId{

            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundException(){
                assertThatThrownBy(() -> categoryService.delete(-1L))
                        .isInstanceOf(CategoryNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("카테고리 생성자와 다른 인증정보가 주어진다면")
        class Context_with_diffrent_authentication{
            Category category;

            @BeforeEach
            void setUp(){
                User otherUser = prepareUser("other");
                category = categoryRespository.save(prepareRequestData("").toEntity(otherUser.getId()));
            }

            @Test
            @DisplayName("카테고리 접근권한이 없다는 예외를 던진다.")
            void it_throw_forbiddenCategoryException(){
                assertThatThrownBy(() -> categoryService.delete(category.getId()))
                        .isInstanceOf(ForbiddenCategoryException.class);
            }
        }
    }

    private CategoryRequestData prepareRequestData(String suffix){
        return CategoryRequestData.builder()
                .title(TITLE + suffix)
                .build();
    }

    private User prepareUser(String suffix){
        return userService.join(userProperties.prepareUserRegistData(suffix));
    }
}