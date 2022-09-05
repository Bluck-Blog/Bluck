package com.choo.blog.domain.comments;

import com.choo.blog.commons.entity.BaseEntity;
import com.choo.blog.domain.comments.dto.CommentRequestData;
import com.choo.blog.domain.posts.entity.Post;
import com.choo.blog.domain.users.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
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

    public boolean hasModifyPermission(Long userId){
        return author.getId().equals(userId);
    }

    public void update(CommentRequestData updateData) {
        content = updateData.getContent();
    }
}
