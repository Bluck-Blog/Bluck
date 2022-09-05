package com.choo.blog.domain.comments.repository;

import com.choo.blog.domain.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
