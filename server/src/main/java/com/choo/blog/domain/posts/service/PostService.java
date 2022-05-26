package com.choo.blog.domain.posts.service;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.categories.repository.CategoryRespository;
import com.choo.blog.domain.posts.entity.LikeType;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.posts.enums.PostLikes;
import com.choo.blog.domain.posts.repository.PostLikesRepository;
import com.choo.blog.domain.posts.repository.PostRepository;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.domain.categories.exceptions.CategoryNotFoundException;
import com.choo.blog.exceptions.ForbiddenPostException;
import com.choo.blog.exceptions.PostNotFoundException;
import com.choo.blog.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    private final UserService userService;

    private final CategoryRespository categoryRespository;

    private final PostLikesRepository postLikesRepository;

    public Page<Post> getPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Post viewPost(Long id){
        Post post = getPost(id);
        post.increaseViewCount();
        return post;
    }

    public Post getPost(Long id){
        Optional<Post> a;
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return post;
    }

    public Post save(PostRequestData saveData){
        User author = userService.getUser(getLoginInfo().getUserId());

        Category category = getCategory(saveData);

        Post post = saveData.createEntity(author);
        post.setCategory(category);
        return postRepository.save(post);
    }

    private Category getCategory(PostRequestData saveData) {
        Category category = categoryRespository.findByUserIdAndId(getLoginInfo().getUserId(), saveData.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(saveData.getCategoryId()));
        return category;
    }

    public Post update(Long id, PostRequestData updateData){
        Post posts = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        UserAuthentication authentication = getLoginInfo();

        if(!posts.hasModifyPermission(authentication.getUserId())){
            throw new ForbiddenPostException(posts.getId());
        }

        Category category = getCategory(updateData);

        posts.update(updateData);
        posts.setCategory(category);

        return posts;
    }

    public void delete(Long id){
        Post post = getPost(id);
        UserAuthentication authentication = getLoginInfo();

        if(!post.getAuthor().getId().equals(authentication.getUserId())){
            throw new ForbiddenPostException(post.getId());
        }

        postRepository.delete(post);
    }

    @Transactional
    public int like(Long postId){
        Long userId = getLoginInfo().getUserId();

        Post post = getPost(postId);

        if(postLikesRepository.existsByPostIdAndUserId(postId, userId)){
            postLikesRepository.deleteByPostIdAndUserId(postId, userId);
        }
        else {
            PostLikes postLikes = new PostLikes(postId, userId, LikeType.LIKE);
            postLikesRepository.save(postLikes);
        }

        return postLikesRepository.countByPostIdAndType(postId, LikeType.LIKE);
    }

    public int dislike(Long postId){
        return 0;
    }



    private UserAuthentication getLoginInfo(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
