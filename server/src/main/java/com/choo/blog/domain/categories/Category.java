package com.choo.blog.domain.categories;

import com.choo.blog.domain.users.User;
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

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;
}
