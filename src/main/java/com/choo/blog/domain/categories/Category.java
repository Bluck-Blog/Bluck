package com.choo.blog.domain.categories;

import com.choo.blog.domain.users.User;
import lombok.*;

import javax.persistence.*;

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
    
    public boolean hasModifyPermission(Long userId){
        return this.userId.equals(userId);
    }
}
