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
    private String name;
    private String nickname;
    private String profileImage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String profileMessage;
    private String introduction;
    private String phone;
    private String verifyCode;

    public UserRegistData prepareUserRegistData(String suffix){
        return UserRegistData.builder()
                .email(email + suffix)
                .password(password + suffix)
                .name(name + suffix)
                .nickname(nickname + suffix)
                .profileImage(profileImage)
                .birthdate(birthDate)
                .profileMessage(profileMessage)
                .introduction(introduction)
                .phone(phone)
                .verifyCode(verifyCode)
                .build();
    }
}
