package com.choo.blog.domain.comments;

import com.choo.blog.domain.BaseEntity;
import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.users.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comments extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post posts;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private boolean secret;

    private int likes;

    private int dislikes;
}
