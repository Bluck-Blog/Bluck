package com.choo.blog.common;

import com.choo.blog.domain.users.dto.UserRegistData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConfigurationProperties(prefix = "user" )
@Getter @Setter
@ToString
public class UserProperties {
    private String email;
    private String password;
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String description;

    public UserRegistData prepareUserRegistData(String suffix){
        return UserRegistData.builder()
                .email(email + suffix)
                .password(password + suffix)
                .nickname(nickname + suffix)
                .birthdate(birthDate)
                .description(description + suffix)
                .build();
    }
}
