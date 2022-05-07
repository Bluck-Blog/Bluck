package com.choo.blog.domain.comments.service;

import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.comments.dto.CommentRequestData;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public Comments save(Long postId, CommentRequestData saveData){
        return null;
    }

    public Comments update(Long commentId, CommentRequestData updateData){
        return null;
    }

    public Comments delete(Long commentId){
        return null;
    }

    public Comments get(Long commentId){
        return null;
    }
}
