package com.choo.blog.domain.comments.service;

import com.choo.blog.common.PostProperties;
import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.service.CategoryService;
import com.choo.blog.domain.categories.service.CategoryServiceTest;
import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.comments.dto.CommentRequestData;
import com.choo.blog.domain.comments.exceptions.ForbiddenCommentException;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.CommentNotFoundException;
import com.choo.blog.exceptions.PostNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    CommentService commentService;
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
        CommentRequestData saveData;

        @BeforeEach
        void setUp(){
            saveData = prepareRequestData( false, "");
        }

        @Nested
        @DisplayName("게시물 id와 댓글이 주어지면")
        class Context_with_postId_and_comments{
            @Test
            @DisplayName("댓글을 생성하고 생성된 댓글을 반환한다.")
            void it_save_comments_and_return_commenst(){
                Comments comments = commentService.save(post.getId(), saveData);
                assertThat(comments.getContent()).isEqualTo(saveData.getContent());
                assertThat(comments.isSecret()).isEqualTo(saveData.isSecret());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class Context_with_invalid_postId{
            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            void it_throw_postNotFoundException(){
                assertThatThrownBy(() -> commentService.save(-1L, saveData))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }
    }

    @Nested
    @DisplayName("댓글 수정은")
    class Describe_update{
        Comments comments;

        @BeforeEach
        void setUp(){
            comments = prepareComment(post.getId(), false, "");
        }

        @Nested
        @DisplayName("comment id와 댓글 내용이 주어지면")
        class Context_with_commentId{
            CommentRequestData updateData;

            @BeforeEach
            void setUp(){
                updateData = prepareRequestData(false, "_NEW");
            }

            @Test
            @DisplayName("댓글을 수정하고 수정된 댓글을 반환한다.")
            void it_update_and_return_comment(){
                Comments updatedComment = commentService.update(comments.getId(), updateData);
                assertThat(updatedComment).isNotNull();
                assertThat(updatedComment.getContent()).isEqualTo(updateData.getContent());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 commentId가 주어진다면")
        class Context_with_non_exist{
            CommentRequestData updateData;

            @BeforeEach
            void setUp(){
                updateData = prepareRequestData(false, "_NEW");
                User other = prepareUser("_other");
                login(other.getId());
            }

           @Test
           @DisplayName("댓글을 찾을 수 없다는 예외를 던진다.")
           void it_throw_commentNotFoundException(){
               assertThatThrownBy(() -> commentService.update(-1L, updateData))
                       .isInstanceOf(CommentNotFoundException.class);
           }
        }

        @Nested
        @DisplayName("댓글 생성자와 다른 인증정보가 주어지면")
        class Context_with_other_session{
            CommentRequestData updateData;

            @BeforeEach
            void setUp(){
                setAuthentication("other");
                updateData = prepareRequestData(false, "_NEW");
            }

            @Test
            @DisplayName("권한이 없다는 예외를 던진다.")
            void it_throw_forbiddenCommentException(){
                assertThatThrownBy(() -> commentService.update(comments.getId(), updateData))
                        .isInstanceOf(ForbiddenCommentException.class);
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

    private CommentRequestData prepareRequestData(boolean secret, String suffix){
        return CommentRequestData.builder()
                .content(CONTENT + suffix)
                .secret(secret)
                .build();
    }

    private Comments prepareComment(Long postId, boolean secret, String suffix){
        return commentService.save(postId, prepareRequestData(secret, suffix));
    }

    private User setAuthentication(String prefix) {
        User user = prepareUser(prefix);
        String accessToken = webTokenUtil.encode(user.getId());
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(UserRole.Authorized, accessToken, user.getId()));
        return user;
    }

}