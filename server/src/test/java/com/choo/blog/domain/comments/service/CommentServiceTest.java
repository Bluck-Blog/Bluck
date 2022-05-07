package com.choo.blog.domain.comments.service;

import com.choo.blog.common.PostProperties;
import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.service.CategoryService;
import com.choo.blog.domain.categories.service.CategoryServiceTest;
import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.security.UserAuthentication;
import com.choo.blog.util.WebTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("댓글 관리")
@ActiveProfiles("test")
class CommentServiceTest {
    private final static String CONTENT = "댓글 내용";
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PostService postService;
    @Autowired
    PostProperties postProperties;
    @Autowired
    UserProperties userProperties;
    @Autowired
    private WebTokenUtil webTokenUtil;

    private User user;
    private Category category;
    private Post post;

    @BeforeEach
    void setUp(){
        user = prepareUser("");
        login(user.getId());
        category = prepareCategory("");
        post = preparePost("");
    }

    @Nested
    @DisplayName("댓글 저장은")
    class Describe_save{
        @BeforeEach
        void setUp(){

        }
        @Nested
        @DisplayName("게시물 id와 댓글이 주어지면")
        class Context_with_postId_and_comments{
            void it_save_comments_and_return_commenst(){

            }
        }
    }

    private void login(Long userId){
        String accessToken = webTokenUtil.encode(userId);
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(UserRole.Authorized, accessToken, user.getId()));
    }

    private User prepareUser(String suffix){
        return userService.join(userProperties.prepareUserRegistData(suffix));
    }

    private Category prepareCategory(String suffix){
        CategoryRequestData categoryRequestData = CategoryServiceTest.prepareRequestData(suffix);
        return categoryService.save(categoryRequestData);
    }

    private Post preparePost(String suffix){
        PostRequestData postRequestData = postProperties.generateRequestData(category, "");
        return postService.save(postRequestData);
    }

}