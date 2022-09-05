package com.choo.blog.domain.posts.repository;

import com.choo.blog.domain.posts.entity.LikeType;
import com.choo.blog.domain.posts.enums.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    //PostLikes deletePostLikesByPostIdAndUserId(Long postId, Long userId);

    Integer deleteByPostIdAndUserId(Long postId, Long userId);

    int countByPostIdAndType(Long postId, LikeType type);
}
