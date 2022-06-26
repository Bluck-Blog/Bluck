package com.choo.blog.domain.controller;

import com.choo.blog.common.BaseControllerTest;
import com.choo.blog.common.PostProperties;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시물 관리")
@Nested
class PostControllerTest extends BaseControllerTest {
    private static final String TITLE = "카테고리 제목";

    private User user;
    private Category category;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostProperties postProperties;

    @BeforeEach
    void setUp() throws Exception {
        commentRepository.deleteAll();
        postRepository.deleteAll();
        categoryRespository.deleteAll();
        userRepository.deleteAll();

        user = prepareUser("");
        category = prepareCategory("");
        session = login(prepareLoginData());
    }

    @AfterEach
    void cleanUp(){
        postRepository.deleteAll();
        categoryRespository.deleteAll();
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("게시물 생성은")
    class Describe_create_post{
        @Nested
        @DisplayName("게시물을 입력받으면")
        class context_with_post{
            PostRequestData saveData;

            @BeforeEach
            public void setUp() throws Exception {
                saveData = postProperties.generateRequestData(category, "");
            }

            @Test
            @DisplayName("게시물을 생성하고 생성된 게시물을 반환한다.")
            public void it_return_new_posts() throws Exception{
                mockMvc.perform(post("/api/posts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(saveData))
                            .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.CREATED.name()))
                        .andExpect(jsonPath("body.id").exists())
                        .andExpect(jsonPath("body.title").value(saveData.getTitle()))
                        .andExpect(jsonPath("body.content").value(saveData.getContent()))
                        .andExpect(jsonPath("body.likes").value(0))
                        .andExpect(jsonPath("body.dislikes").value(0))
                        .andExpect(jsonPath("body.openType").value(saveData.getOpenType().toString()))
                        .andExpect(jsonPath("body.view").value(0))
                        .andDo(document(
                                "create-posts",
                                requestHeaders(
                                        headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                                ),
                                requestFields(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                                        fieldWithPath("openType").type(JsonFieldType.STRING).description("게시물 공개 범위"),
                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 id")
                                ),
                                relaxedResponseFields(
                                        fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("생성된 게시물 번호"),
                                        fieldWithPath("body.author").type(JsonFieldType.OBJECT).description("게시물 작성자"),
                                        fieldWithPath("body.title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시물 내용"),
                                        //fieldWithPath("category").description("게시물 카테고리"),
                                        fieldWithPath("body.likes").type(JsonFieldType.NUMBER).description("게시물 좋아요 갯수"),
                                        fieldWithPath("body.dislikes").type(JsonFieldType.NUMBER).description("게시물 싫어요 갯수"),
                                        fieldWithPath("body.view").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                        fieldWithPath("body.openType").type(JsonFieldType.STRING).description("게시물 공개 범위"),
                                        fieldWithPath("body.commentsList").type(JsonFieldType.ARRAY).description("게시물 댓글 목록"),
                                        fieldWithPath("body.createDate").type(JsonFieldType.STRING).description("게시물 생성 시간"),
                                        fieldWithPath("body.modifiedDate").type(JsonFieldType.STRING).description("게시물 수정 시간")
                                )

                        ));
            }
        }

        @Nested
        @DisplayName("빈 데이터를 입력받으면")
        class context_with_empty_data{
            PostRequestData saveData;
            @BeforeEach
            public void setUp(){
                saveData = new PostRequestData();
            }

            @Test
            @DisplayName("에러코드 400를 반환한다.")
            public void it_return_bad_request() throws Exception{
                mockMvc.perform(post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(saveData))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("errors[0].objectName").exists())
                        .andExpect(jsonPath("errors[0].code").exists())
                        .andExpect(jsonPath("errors[0].rejectedValue").hasJsonPath());
            }
        }

        @Nested
        @DisplayName("잘못된 인증정보로 요청하면")
        class Context_with_wrong_accessToken{
            PostRequestData saveData;

            @BeforeEach
            public void setUp() throws Exception {
                saveData = postProperties.generateRequestData(category, "");
            }

            @Test
            @DisplayName("에러코드 401을 반환한다")
            public void it_return_unAuthorized() throws Exception{
                mockMvc.perform(post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(saveData))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken() + "wrong"))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("인증정보가 없으면")
        class Context_with_no_accessToken{
            PostRequestData saveData;

            @BeforeEach
            public void setUp() throws Exception {
                saveData = postProperties.generateRequestData(category, "");
            }

            @Test
            @DisplayName("에러코드 401을 반환한다.")
            public void it_return_unAuthorize() throws Exception{
                mockMvc.perform(post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(saveData)))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }

        }
    }

    @Nested
    @DisplayName("게시물 수정은")
    class Descrive_update{
        @Nested
        @DisplayName("게시물 id와 수정 정보가 주어지면")
        class context_with_postId_and_update_info{
            PostRequestData updateData;
            Post post;

            @BeforeEach
            void setUp() throws Exception {
                updateData = postProperties.generateRequestData(category, "_NEW");
                post = preparePost("");
            }

            @Test
            @DisplayName("게시물을 수정하고 수정된 게시물을 반환한다.")
            void it_return_updated_posts() throws Exception {
                mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/posts/{id}",post.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(objectMapper.writeValueAsString(updateData))
                            .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.OK.name()))
                        .andExpect(jsonPath("body.id").value(post.getId()))
                        .andExpect(jsonPath("body.title").value(updateData.getTitle()))
                        .andExpect(jsonPath("body.content").value(updateData.getContent()))
                        .andExpect(jsonPath("body.likes").value(post.getLikes()))
                        .andExpect(jsonPath("body.dislikes").value(post.getDislikes()))
                        .andExpect(jsonPath("body.openType").value(updateData.getOpenType().toString()))
                        .andExpect(jsonPath("body.view").value(post.getView()))
                        .andDo(document(
                                "update-post",
                                requestHeaders(
                                        headerWithName(HttpHeaders.AUTHORIZATION).description("인증 토큰")
                                ),
                                pathParameters(
                                        parameterWithName("id").description("수정할 게시물 pk")
                                ),
                                requestFields(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 pk"),
                                        fieldWithPath("openType").type(JsonFieldType.STRING).description("게시물 공개 범위")
                                ),
                                relaxedResponseFields(
                                        fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("게시물 번호"),
                                        fieldWithPath("body.author").type(JsonFieldType.OBJECT).description("게시물 작성자"),
                                        fieldWithPath("body.title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시물 내용"),
                                        //fieldWithPath("category").description("게시물 카테고리"),
                                        fieldWithPath("body.likes").type(JsonFieldType.NUMBER).description("게시물 좋아요 갯수"),
                                        fieldWithPath("body.dislikes").type(JsonFieldType.NUMBER).description("게시물 싫어요 갯수"),
                                        fieldWithPath("body.view").type(JsonFieldType.NUMBER).description("게시물 조회 수"),
                                        fieldWithPath("body.openType").type(JsonFieldType.STRING).description("게시물 공개 범위"),
                                        fieldWithPath("body.commentsList").type(JsonFieldType.ARRAY).description("게시물 댓글 목록"),
                                        fieldWithPath("body.createDate").type(JsonFieldType.STRING).description("게시물 생성 시간"),
                                        fieldWithPath("body.modifiedDate").type(JsonFieldType.STRING).description("게시물 수정 시간")
                                )
                        ));
            }

            @Nested
            @DisplayName("잘못된 인증정보로 요청하면")
            class Context_with_wrong_accessToken{
                PostRequestData updateData;
                Post post;

                @BeforeEach
                public void setUp() throws Exception {
                    post = preparePost("");
                    updateData = postProperties.generateRequestData(category, "_NEW");
                }

                @Test
                @DisplayName("에러코드 401을 반환한다")
                public void it_return_unAuthorized() throws Exception{
                    mockMvc.perform(patch("/api/posts/{id}",post.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaTypes.HAL_JSON)
                                    .content(objectMapper.writeValueAsString(updateData))
                                    .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken() + "wrong"))
                            .andDo(print())
                            .andExpect(status().isUnauthorized());
                }
            }

            @Nested
            @DisplayName("인증정보가 없으면")
            class Context_with_no_accessToken{
                Post post;
                PostRequestData updateData;

                @BeforeEach
                public void setUp() throws Exception {
                    post = preparePost("");
                    updateData = postProperties.generateRequestData(category, "NEW");
                }

                @Test
                @DisplayName("에러코드 401을 반환한다.")
                public void it_return_unAuthorize() throws Exception{
                    mockMvc.perform(patch("/api/posts/{id}",post.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaTypes.HAL_JSON)
                                    .content(objectMapper.writeValueAsString(updateData)))
                            .andDo(print())
                            .andExpect(status().isUnauthorized());
                }

            }
        }

        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class context_with_non_exist_postId{
            PostRequestData updateData;

            @BeforeEach
            public void setUp(){
                updateData = postProperties.generateRequestData(category, "_NEW");
            }
            @Test
            @DisplayName("에러코드 404를 반환한다.")
            public void it_return_postNotFoundException() throws Exception {
                mockMvc.perform(patch("/api/posts/{id}", -1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.NOT_FOUND.name()))
                        .andExpect(jsonPath("body.message").value(Matchers.containsString("-1")));
                        ;

            }
        }

        @Nested
        @DisplayName("빈 데이터를 입력받는다면")
        class Context_with_wrong_data{
            PostRequestData updateData;

            @BeforeEach
            public void setUp(){
                updateData = new PostRequestData();
            }

            @Test
            @DisplayName("에러코드 400을 반환한다")
            public void it_return_badRequest() throws Exception{
                mockMvc.perform(post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(updateData))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("errors[0].objectName").exists())
                        .andExpect(jsonPath("errors[0].code").exists())
                        .andExpect(jsonPath("errors[0].rejectedValue").hasJsonPath());
            }
        }
    }

    @Nested
    @DisplayName("게시물 목록 조회는")
    class Descrive_get_posts{
        int page = 0;
        int pageSize = 10;
        int size = 30;

        Pageable pageable;

        @BeforeEach
        public void setUp() throws Exception{
            pageable = PageRequest.of(page, pageSize);

            for(int i = 0; i < size; i++){
                preparePost(i + "");
            }
        }

        @Nested
        @DisplayName("게시물 조회 조건을 입력받으면")
        class Context_with_search_condition{
            @Test
            @DisplayName("조회 결과를 반환한다.")
            public void it_return_paging_posts() throws Exception {
                mockMvc.perform(get("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(pageable))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body.page").exists())
                        .andDo(document(
                                "get-posts",
                                relaxedRequestFields(
                                        fieldWithPath("pageNumber").description("현재 페이지"),
                                        fieldWithPath("pageSize").description("페이지당 게시물 갯수"),
                                        fieldWithPath("offset").description("offset"),
                                        fieldWithPath("paged").description("페이징 처리 여부")
                                ),
                                relaxedResponseFields(
                                        fieldWithPath("status").description("HTTP 상태 코드"),
                                        fieldWithPath("code").description("응답 코드"),
                                        fieldWithPath("body.links[].href").description("url / first : 첫 페이지, self : 현재 페이지, next : 다음 페이지, last : 마지막 페이지"),
                                        fieldWithPath("body.content[].createDate").description("생성일"),
                                        fieldWithPath("body.content[].modifiedDate").description("수정일"),
                                        fieldWithPath("body.content[].id").description("id"),
                                        fieldWithPath("body.content[].author").description("게시물 생성자 정보"),
                                        fieldWithPath("body.content[].title").description("게시물 제목"),
                                        fieldWithPath("body.content[].likes").description("좋아요 수"),
                                        fieldWithPath("body.content[].dislikes").description("싫어요 수"),
                                        fieldWithPath("body.content[].openType").description("게시물 공개 타입"),
                                        fieldWithPath("body.content[].view").description("조회 횟수")
                                )

                        ))
                        ;
            }
        }

        @Nested
        @DisplayName("게시물 저자와 다른 인증정보로 요청하면")
        class Context_with_other_authentication{
            String accessToken;

            @BeforeEach
            public void setUp() throws Exception{
                User other = prepareUser("other");
                accessToken = webTokenUtil.encode(other.getId());
            }
            @Test
            @DisplayName("update-url과 delete-url을 포함하지 않는다")
            public void it_return_paging_posts_without_deleteUrl_and_updateUrl() throws Exception{
                mockMvc.perform(get("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .content(objectMapper.writeValueAsString(pageable))
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + accessToken))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body.page").exists());
            }
        }
    }

    @Nested
    @DisplayName("게시물 조회는")
    class Describe_get_post{
        Post post;

        @BeforeEach
        public void setUp() throws Exception {
            post = preparePost("");
        }

        @Nested
        @DisplayName("존재하는 게시물 id가 주어지면")
        class Context_with_exist_postId{

            @Test
            @DisplayName("id에 해당하는 게시물을 반환한다.")
            void it_return_post() throws Exception {
                mockMvc.perform(RestDocumentationRequestBuilders.get("/api/posts/{id}", post.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body.title").value(post.getTitle()))
                        .andExpect(jsonPath("body.content").value(post.getContent()))
                        .andExpect(jsonPath("body.likes").value(post.getLikes()))
                        .andExpect(jsonPath("body.dislikes").value(post.getDislikes()))
                        .andExpect(jsonPath("body.view").value(post.getView() + 1))
                        .andDo(document(
                                "get-post",
                                pathParameters(
                                        parameterWithName("id").description("게시물 id")
                                ),
                                relaxedResponseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("http 상태 코드"),
                                        fieldWithPath("body.id").type(JsonFieldType.NUMBER).description("게시물 id"),
                                        fieldWithPath("body.author").type(JsonFieldType.OBJECT).description("게시물 등록자"),
                                        fieldWithPath("body.title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("body.content").type(JsonFieldType.STRING).description("게시물 내용"),
                                        fieldWithPath("body.category").type(JsonFieldType.OBJECT).description("게시물 카테고리"),
                                        fieldWithPath("body.likes").type(JsonFieldType.NUMBER).description("게시물 좋아요 수"),
                                        fieldWithPath("body.dislikes").type(JsonFieldType.NUMBER).description("게시물 싫어요 수"),
                                        fieldWithPath("body.openType").type(JsonFieldType.STRING).description("게시물 공개방식"),
                                        fieldWithPath("body.view").type(JsonFieldType.NUMBER).description("게시물 조회수"),
                                        fieldWithPath("body.commentsList").type(JsonFieldType.ARRAY).description("게시물 댓글 목록"),
                                        fieldWithPath("body.createDate").type(JsonFieldType.STRING).description("게시물 생성일"),
                                        fieldWithPath("body.modifiedDate").type(JsonFieldType.STRING).description("게시물 수정일"),
                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("에러코드")
                                )
                        ));
            }
        }

        @Nested
        @DisplayName("게시물 저자와 다른 인증정보로 요청하면")
        class Context_with_other_authentication{
            String accessToken;
            @BeforeEach
            public void setUp() throws Exception{
                User other = prepareUser("other");
                accessToken = webTokenUtil.encode(other.getId());
            }
            @Test
            @DisplayName("id에 해당하는 게시물을 반환한다.")
            void it_return_post() throws Exception {
                mockMvc.perform(get("/api/posts/{id}", post.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + accessToken))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("body.title").value(post.getTitle()))
                        .andExpect(jsonPath("body.content").value(post.getContent()))
                        .andExpect(jsonPath("body.likes").value(post.getLikes()))
                        .andExpect(jsonPath("body.dislikes").value(post.getDislikes()))
                        .andExpect(jsonPath("body.view").value(post.getView() + 1));
            }
        }

        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class Context_with_non_exist_postId{
            @Test
            @DisplayName("에러코드 404를 반환한다.")
            void it_return_notFound() throws Exception {
                mockMvc.perform(get("/api/posts/{id}", -1)
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.NOT_FOUND.name()))
                        .andExpect(jsonPath("body.message").value(Matchers.containsString("-1")));
            }
        }
    }

    @Nested
    @DisplayName("게시물 삭제는")
    class Descrive_delete_post{
        @Nested
        @DisplayName("존재하는 게시물 id가 주어지면")
        class Context_with_exist_postId{
            Post post;

            @BeforeEach
            void setUp() throws Exception {
                post = preparePost("");
            }

            @Test
            @DisplayName("게시물을 삭제하고 HTTP code 200을 반환한다")
            void it_return_ok() throws Exception {
                mockMvc.perform(delete("/api/posts/{id}", post.getId())
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 게시물 id가 주어지면")
        class Context_with_non_eixst_postId{

            @Test
            @DisplayName("에러코드 404를 반환한다.")
            void it_return_notFound() throws Exception{
                mockMvc.perform(delete("/api/posts/{id}", -1)
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("status").value(HttpStatus.NOT_FOUND.name()))
                        .andExpect(jsonPath("body.message").value(Matchers.containsString("-1")));
            }
        }

        @Nested
        @DisplayName("잘못된 인증정보로 요청하면")
        class Context_with_wrong_accessToken{
            Post post;

            @BeforeEach
            public void setUp() throws Exception {
                post = preparePost("");
            }

            @Test
            @DisplayName("에러코드 401을 반환한다")
            public void it_return_unAuthorized() throws Exception{
                mockMvc.perform(delete("/api/posts/{id}",post.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON)
                                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken() + "wrong"))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        @DisplayName("인증정보가 없으면")
        class Context_with_no_accessToken{
            Post post;

            @BeforeEach
            public void setUp() throws Exception {
                post = preparePost("");
            }

            @Test
            @DisplayName("에러코드 401을 반환한다.")
            public void it_return_unAuthorize() throws Exception{
                mockMvc.perform(delete("/api/posts/{id}",post.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaTypes.HAL_JSON))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
            }

        }

    }

    private Post preparePost(String suffix) throws Exception{
        MvcResult result = mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(postProperties.generateRequestData(category, suffix)))
                .header(HttpHeaders.AUTHORIZATION,  "Bearer " + session.getAccessToken()))
                .andReturn();
        String content = getBody(result);
        return objectMapper.readValue(content, Post.class);
    }

    private Category prepareCategory(String suffix){
        CategoryRequestData saveData = CategoryRequestData.builder()
                .title(TITLE + suffix)
                .build();
        return categoryRespository.save(saveData.toEntity(user.getId()));
    }
}