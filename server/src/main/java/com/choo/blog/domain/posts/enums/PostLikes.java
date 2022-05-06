package com.choo.blog.domain.posts.enums;

import com.choo.blog.domain.enums.LikeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_likes_id")
    private Long id;

    private Long postId;

    private Long userId;

    private LikeType type;

    @CreatedDate
    private LocalDateTime createDate;

    public PostLikes(Long postId, Long userId, LikeType type) {
        this.postId = postId;
        this.userId = userId;
        this.type = type;
    }
}
