package com.choo.blog.domain.posts;

import com.choo.blog.domain.BaseEntity;
import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.comments.Comments;
import com.choo.blog.domain.posts.dto.PostRequestData;
import com.choo.blog.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int likes;

    private int dislikes;

    @Enumerated
    private PostOpenType openType;

    private int view;

    @OneToMany(mappedBy = "posts")
    private List<Comments> commentsList = new ArrayList<>();

    public boolean hasModifyPermission(Long userId){
        return author.getId().equals(userId);
    }

    public void update(PostRequestData requestData){
        title = requestData.getTitle();
        content = requestData.getContent();
        openType = requestData.getOpenType();
    }
}
