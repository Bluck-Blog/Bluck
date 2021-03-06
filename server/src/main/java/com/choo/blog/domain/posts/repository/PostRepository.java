package com.choo.blog.domain.posts.repository;

import com.choo.blog.domain.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
