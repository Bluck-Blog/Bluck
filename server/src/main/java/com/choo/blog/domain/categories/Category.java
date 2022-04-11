package com.choo.blog.domain.categories;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String title;

    private int depth;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> childrens;
}
