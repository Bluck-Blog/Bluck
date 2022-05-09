package com.choo.blog.service.posts;

import com.choo.blog.common.UserProperties;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.posts.enums.PostOpenType;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.UserRole;
import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.CategoryNotFoundException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("게시물 관리")
@ActiveProfiles("test")
@WithMockCustomUser
class PostServiceTest {
    private static final String TITLE = "게시물 제목";
    private static final String CONTENT = "게시물 내용";

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    UserProperties userProperties;

    @Autowired
    WebTokenUtil webTokenUtil;

    Category category;

    User user;

    @BeforeEach
    void setUp(){
        user = setAuthentication("");

        category = prepareCategory("", user);
    }

    @Nested
    @DisplayName("게시물 저장은")
    class Descrive_save{

        PostRequestData saveData;
        @BeforeEach
        void setUp(){
            saveData = prepareRequestData("");
        }

        @Nested
        @DisplayName("게시물을 입력받으면")
        class Context_with_post{

            @Test
            @DisplayName("저장하고 저장된 게시물을 반환한다.")
            void it_return_post(){
                Post posts = postService.save(saveData);

                assertThat(posts.getTitle()).isEqualTo(saveData.getTitle());
                assertThat(posts.getContent()).isEqualTo(saveData.getContent());
                assertThat(posts.getOpenType()).isEqualTo(saveData.getOpenType());
                assertThat(posts.getView()).isEqualTo(0);

                UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
                assertThat(posts.getAuthor().getId()).isEqualTo(authentication.getUserId());
                assertThat(posts.getCategory().getTitle()).isEqualTo(category.getTitle());
            }
            @Nested
            @DisplayName("존재하지 않는 categoryId가 주어진다면")
            class Context_with_non_exist_categoryId{

                @BeforeEach
                void setUp(){
                    ReflectionTestUtils.setField(saveData, "categoryId", -1L);
                }
                @Test
                @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
                void it_throw_categoryNotFoundException(){
                    assertThatThrownBy(() -> postService.save(saveData))
                            .isInstanceOf(CategoryNotFoundException.class);
                }

            }
        }
        @Nested
        @DisplayName("주어진 categoryId의 userId가 인증정보와 다르다면")
        class Context_with_diffren_userId{
            @BeforeEach
            void setUp(){
                setAuthentication("other");
            }

            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundExceptoin(){
                assertThatThrownBy(() -> postService.save(saveData))
                        .isInstanceOf(CategoryNotFoundException.class);
            }
        }
    }
    @Nested
    @DisplayName("게시물 수정은")
    class Descrive_update {

        PostRequestData updateData;

        @BeforeEach
        public void setUp() {
            updateData = prepareRequestData("_NEW");
        }

        @Nested
        @DisplayName("등록된 게시물 id가 주어진다면")
        class Context_with_exist_postId {

            Post posts;

            @BeforeEach
            public void setUp() {
                posts = postService.save(prepareRequestData(""));
            }

            @Test
            @DisplayName("id에 해당하는 게시물을 수정하고 수정된 게시물을 반환한다.")
            void it_update_post_return() {
                Post updatePost = postService.update(posts.getId(), updateData);

                assertThat(updatePost.getTitle()).isEqualTo(updateData.getTitle());
                assertThat(updatePost.getContent()).isEqualTo(updateData.getContent());
                assertThat(updatePost.getOpenType()).isEqualTo(updateData.getOpenType());
            }

        }

        @Nested
        @DisplayName("등록되지 않은 게시물 id가 주어진다면")
        class Context_with_not_exist_postId {

            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            public void it_throw_postNotFoundException() {
                assertThatThrownBy(() -> postService.update(1111L, updateData))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("게시물 생성 저자와 다른 인증정보가 주어진다면")
        class Context_with_wrong_authentication {
            Post post;
            PostRequestData updateData;

            @BeforeEach
            void setUp() {
                User author = prepareUser("other");
                updateData = prepareRequestData("_NEW");
                post = postRepository.save(prepareRequestData("").createEntity(author));

            }

            @Test
            @DisplayName("게시물 수정권한이 없다는 예외를 던진다.")
            public void it_throw_accessForbiddenModifyPost() {
                assertThatThrownBy(() -> postService.update(post.getId(), updateData))
                        .isInstanceOf(ForbiddenPostException.class)
                        .hasMessageContaining(post.getId().toString());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 categoryId가 주어진다면")
        class Context_with_non_exist_categoryId {
            Post post;

            @BeforeEach
            void setUp() {
                post = postService.save(prepareRequestData(""));
                ReflectionTestUtils.setField(updateData, "categoryId", -1L);
            }

            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundException() {
                assertThatThrownBy(() -> postService.update(post.getId(), updateData))
                        .isInstanceOf(CategoryNotFoundException.class);
            }

        }

        @Nested
        @DisplayName("주어진 categoryId의 userId가 인증정보와 다르다면")
        class Context_with_diffren_userId {
            Post post;

            @BeforeEach
            void setUp() {
                User othreUser = prepareUser("");
                Category otherCategory = prepareCategory("_other", othreUser);
                ReflectionTestUtils.setField(updateData, "categoryId", otherCategory.getId());
                post = postService.save(prepareRequestData(""));
            }

            @Test
            @DisplayName("카테고리를 찾을 수 없다는 예외를 던진다.")
            void it_throw_categoryNotFoundExceptoin() {
                assertThatThrownBy(() -> postService.update(post.getId(), updateData))
                        .isInstanceOf(CategoryNotFoundException.class);
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
                Post findPost = postService.viewPost(this.post.getId());

                assertThat(findPost.getView()).isEqualTo(post.getView() + 1);
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
                assertThatThrownBy(() -> postService.viewPost(-1L))
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
            postRepository.deleteAll();
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
                posts = postRepository.save(prepareRequestData("").createEntity(user));
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

    @Nested
    @DisplayName("게시물 좋아요는")
    class Describe_like{
        Post post;

        @BeforeEach
        void setUp(){
            post = preparePost("");
        }

        @Nested
        @DisplayName("게시물 id가 주어지면")
        class Context_with_postId{
            @Test
            @DisplayName("해당 게시물의 좋아요를 추가하고 좋아요수를 반환한다.")
            void it_append_post_likes_and_return_likeCnt(){
                int beforeLikeCnt = post.getLikes();
                int likeCnt = postService.like(post.getId());
                assertThat(likeCnt).isEqualTo(beforeLikeCnt + 1);
            }
        }

        @Nested
        @DisplayName("이미 좋아요를 누른 걔정으로 좋아요를 요청하면")
        class Context_with_already_request{
            @Test
            @DisplayName("해당 게시물의 좋아요를 제거하고 좋아요 수를 반환한다.")
            void it_remove_post_likes_and_return_likeCnt(){
                int beforeLikeCnt = post.getLikes();
                int likeCnt = postService.like(post.getId());
                assertThat(likeCnt).isEqualTo(beforeLikeCnt - 1);
            }
        }
        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class Context_with_non_exist_postId{
            @Test
            @DisplayName("게시물을 찾을 수 없다는 예외를 던진다")
            void it_throw_postNotFoundException(){
                assertThatThrownBy(() -> postService.like(-1L))
                        .isInstanceOf(PostNotFoundException.class);
            }
        }
    }
    public User prepareUser(String suffix){
        return userService.join(userProperties.prepareUserRegistData(suffix));
    }

    public Category prepareCategory(String suffiex, User user){
        Category category = CategoryRequestData.builder()
                .title("category 제목" +suffiex)
                .build().toEntity(user.getId());

        return categoryRespository.save(category);
    }

    private PostRequestData prepareRequestData(String suffix){
        return PostRequestData.builder()
                .title(TITLE + suffix)
                .openType(PostOpenType.ALL)
                .content(CONTENT + suffix)
                .categoryId(category.getId())
                .build();
    }

    private Post preparePost(String suffix){
        return postService.save(prepareRequestData(suffix));
    }

    private User setAuthentication(String prefix) {
        User user = prepareUser(prefix);
        String accessToken = webTokenUtil.encode(user.getId());
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(UserRole.Authorized, accessToken, user.getId()));
        return user;
    }
}