package com.choo.blog.domain.comments.service;

import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.comments.dto.CommentRequestData;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public Comments save(Long postId, CommentRequestData saveData){
        Post post = postService.getPost(postId);
        User user = userService.getUser(getLoginInfo().getUserId());

        return commentRepository.save(saveData.createEntity(user, post));
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

    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
