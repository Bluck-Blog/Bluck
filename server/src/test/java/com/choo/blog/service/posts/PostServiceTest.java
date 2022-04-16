package com.choo.blog.service.posts;

import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.posts.PostOpenType;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.ForbiddenPostException;
import com.choo.blog.exceptions.PostNotFoundException;
import com.choo.blog.security.UserAuthentication;
import com.choo.blog.session.WithMockCustomUser;
import com.choo.blog.util.WebTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("게시물 관리")
@WithMockCustomUser
class PostServiceTest {
    private static final String TITLE = "게시물 제목";
    private static final String CONTENT = "게시물 내용";

    private static final String EMAIL = "choo@email.com";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "choo";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1995,11,18);
    private static final String DESCRIPTION = "description";


    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    WebTokenUtil webTokenUtil;

    @BeforeEach
    void setUp(){
        User user = prepareUser("");
        String accessToken = webTokenUtil.encode(user.getId());
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(UserRole.Authorized, accessToken, user.getId()));
    }

    @Nested
    @DisplayName("게시물 저장은")
    class Descrive_save{

        @Nested
        @DisplayName("게시물을 입력받으면")
        class Context_with_post{
            PostRequestData saveData;

            @BeforeEach
            void setUp(){
                saveData = prepareRequestData("");
            }

            @Test
            @DisplayName("저장하고 저장된 게시물을 반환한다.")
            void it_return_post(){
                Post posts = postService.save(saveData);

                assertThat(posts.getTitle()).isEqualTo(saveData.getTitle());
                assertThat(posts.getContent()).isEqualTo(saveData.getContent());
                assertThat(posts.getOpenType()).isEqualTo(saveData.getOpenType());
                assertThat(posts.getLikes()).isEqualTo(0);
                assertThat(posts.getDislikes()).isEqualTo(0);
                assertThat(posts.getView()).isEqualTo(0);

                UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
                assertThat(posts.getAuthor().getId()).isEqualTo(authentication.getUserId());
            }
        }
    }

    @Nested
    @DisplayName("게시물 수정은")
    class Descrive_update{
        PostRequestData updateData;

        @BeforeEach
        public void setUp(){
            updateData = prepareRequestData("_NEW");
        }

        @Nested
        @DisplayName("등록된 게시물 id가 주어진다면")
        class Context_with_exist_postId{
            Post posts;

            @BeforeEach
            public void setUp(){
                posts = postService.save(prepareRequestData(""));
            }

            @Test
            @DisplayName("id에 해당하는 게시물을 수정하고 수정된 게시물을 반환한다.")
            void it_update_post_return(){
                Post updatePost = postService.update(posts.getId(), updateData);

                assertThat(updatePost.getTitle()).isEqualTo(updateData.getTitle());
                assertThat(updatePost.getContent()).isEqualTo(updateData.getContent());
                assertThat(updatePost.getOpenType()).isEqualTo(updateData.getOpenType());
            }
        }

        @Nested
        @DisplayName("등록되지 않은 게시물 id가 주어진다면")
        class Context_with_not_exist_postId{
            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            public void it_throw_postNotFoundException(){
                assertThatThrownBy(() -> postService.update(1111L, updateData))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("게시물 생성 저자와 다른 인증정보가 주어진다면")
        class Context_with_wrong_authentication{
            Post post;
            PostRequestData updateData;

            @BeforeEach
            void setUp(){
                User author = prepareUser("other");
                updateData = prepareRequestData("_NEW");
                post = postRepository.save(prepareRequestData("").createEntity(author));

            }

            @Test
            @DisplayName("게시물 수정권한이 없다는 예외를 던진다.")
            public void it_throw_accessForbiddenModifyPost(){
                assertThatThrownBy(() -> postService.update(post.getId(), updateData))
                        .isInstanceOf(ForbiddenPostException.class)
                        .hasMessageContaining(post.getId().toString());
            }
        }
    }

    @Nested
    @DisplayName("게시물 상세 조회는")
    class Describe_find{
        @Nested
        @DisplayName("존재하는 게시물 id가 주어지면")
        class Context_with_exist_postId{
            Post post;

            @BeforeEach
            void setUp() throws Exception {
                post = postService.save(prepareRequestData(""));
            }

            @Test
            @DisplayName("id 에 해당하는 게시물을 반환한다")
            void it_return_post(){
                Post findPost = postService.getPost(this.post.getId());

                assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
                assertThat(findPost.getContent()).isEqualTo(post.getContent());
                assertThat(findPost.getOpenType()).isEqualTo(post.getOpenType());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class Context_with_non_exsit_postId{
            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            void it_throw_postNotFoundException(){
                assertThatThrownBy(() -> postService.getPost(-1L))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }
    }

    @Nested
    @DisplayName("게시물 목록 조회는")
    class Descrive_findAll{
        int page = 0;
        int pageSize = 10;
        int size = 30;

        Pageable pageable;

        @BeforeEach
        public void setUp(){
            pageable = PageRequest.of(page,pageSize);

            User author = prepareUser("");

            IntStream.range(0, size).forEach(i ->{
                postRepository.save(prepareRequestData(i + "").createEntity(author));
            });
        }

        @Nested
        @DisplayName("조회조건을 입력받으면")
        class Context_with_search_condition{
            @Test
            @DisplayName("조회 결과를 반환한다.")
            public void it_return_paging_posts(){
                Page<Post> posts = postService.getPosts(pageable);
                assertThat(posts.getTotalElements()).isEqualTo(size);
                assertThat(posts.getTotalPages()).isEqualTo(size / pageSize);
                assertThat(posts.getNumberOfElements()).isEqualTo(pageSize);
            }
        }
    }

    @Nested
    @DisplayName("게시물 삭제는")
    class Descrive_delete{
        @Nested
        @DisplayName("등록된 게시물 id가 주어진다면")
        class context_with_exist_postId{
            Post posts;
            @BeforeEach
            public void setUp(){
                User author = userService.join(prepareUserRegistData(""));
                posts = postRepository.save(prepareRequestData("").createEntity(author));
            }
            @Test
            @DisplayName("게시물을 삭제한다.")
            void it_delete_post(){
                postService.delete(posts.getId());

                Optional<Post> optionalPosts = postRepository.findById(posts.getId());
                assertThat(optionalPosts).isEmpty();
            }
        }

        @Nested
        @DisplayName("등록되지 않은 게시물 id가 주어진다면")
        class Context_with_non_exise_postId{
            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            public void it_throw_postNotFoundException(){
                assertThatThrownBy(()-> postService.delete(-1L))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("게시물 생성 저자와 다른 인증정보가 주어진다면")
        class Context_with_wrong_authentication{
            Post post;

            @BeforeEach
            void setUp(){
                User author = prepareUser("other");
                post = postRepository.save(prepareRequestData("").createEntity(author));

            }

            @Test
            @DisplayName("게시물 수정권한이 없다는 예외를 던진다.")
            public void it_throw_accessForbiddenModifyPost(){
                assertThatThrownBy(() -> postService.delete(post.getId()))
                        .isInstanceOf(ForbiddenPostException.class)
                        .hasMessageContaining(post.getId().toString());
            }
        }

    }

    public User prepareUser(String suffix){
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

    private PostRequestData prepareRequestData(String suffix){
        return PostRequestData.builder()
                .title(TITLE + suffix)
                .openType(PostOpenType.ALL)
                .content(CONTENT + suffix)
                .build();
    }
}