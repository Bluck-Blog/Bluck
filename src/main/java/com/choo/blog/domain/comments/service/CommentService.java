package com.choo.blog.domain.comments.service;

import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.comments.dto.CommentRequestData;
import com.choo.blog.domain.comments.exceptions.ForbiddenCommentException;
import com.choo.blog.domain.comments.repository.CommentRepository;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.posts.service.PostService;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.CommentNotFoundException;
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
        Comments comments = get(commentId);

        System.out.println(comments);

        UserAuthentication authentication = getLoginInfo();

        if(!comments.hasModifyPermission(authentication.getUserId())){
            throw new ForbiddenCommentException(commentId);
        }

        comments.update(updateData);
        return comments;
    }

    public void delete(Long commentId){
        Comments comments = get(commentId);
        UserAuthentication authentication = getLoginInfo();
        if(!comments.hasModifyPermission(authentication.getUserId())){
            throw new ForbiddenCommentException(commentId);
        }

        commentRepository.delete(comments);
    }

    private Comments get(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
