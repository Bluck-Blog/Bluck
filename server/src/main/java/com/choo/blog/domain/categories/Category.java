package com.choo.blog.domain.categories;

import com.choo.blog.domain.categories.dto.CategoryRequestData;
import com.choo.blog.domain.posts.Post;
import com.choo.blog.domain.users.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Setter
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> postList;

    public boolean hasModifyPermission(Long userId){
        return userId.equals(userId);
    }
}
