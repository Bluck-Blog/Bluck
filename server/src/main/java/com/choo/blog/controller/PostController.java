package com.choo.blog.controller;

import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.posts.dto.PostModel;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.exceptions.InvalidParameterException;
import com.choo.blog.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(@RequestBody @Valid PostRequestData saveData, BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }

        Post posts = postService.save(saveData);
        PostModel postModel = new PostModel(posts);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(PostController.class).slash(posts.getId());
        URI createdUri = selfLinkBuilder.toUri();

        return ResponseEntity.created(createdUri).body(postModel);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updatePost(@PathVariable Long id,
                                     @RequestBody @Valid PostRequestData updateData,
                                     BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }

        Post post = postService.update(id, updateData);
        PostModel postModel = new PostModel(post);

        return ResponseEntity.ok(postModel);
    }

    @GetMapping
    public ResponseEntity queryPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler){
        Page<Post> page = postService.getPosts(pageable);

        PagedModel<PostModel> postModels = assembler.toModel(page, e -> new PostModel(e));

        return ResponseEntity.ok(postModels);
    }

    @GetMapping("{id}")
    public ResponseEntity getPost(@PathVariable Long id){
        Post post = postService.getPost(id);

        PostModel postModel = new PostModel(post);

        return ResponseEntity.ok(postModel);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deletePost(@PathVariable Long id){
        postService.delete(id);

        return ResponseEntity.ok().build();
    }
}
