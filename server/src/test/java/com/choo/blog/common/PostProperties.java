package com.choo.blog.common;

import com.choo.blog.domain.categories.Category;
import com.choo.blog.domain.posts.enums.PostOpenType;
import com.choo.blog.domain.posts.dto.PostRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "post")
@Getter
@Setter
@ToString
public class PostProperties {
    private String TITLE ;
    private String CONTENT;

    public PostRequestData generateRequestData(Category category, String suffix){
        return PostRequestData.builder()
                .title(TITLE + suffix)
                .content(CONTENT + suffix)
                .openType(PostOpenType.ALL)
                .categoryId(category.getId())
                .build();
    }
}
