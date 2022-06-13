package com.choo.blog.common;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    private MockHttpSession session;

    @Autowired
    private UserService userService;

    public User prepareUser(String suffix, String code){
        UserRegistData registData = prepareUserRegistData(suffix);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ReflectionTestUtils.setField(registData, "verifyCode", code);
        return userService.join(registData);
    }

    public void cleanUp(){
        session.clearAttributes();
    }


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

    public MockHttpSession generateVerifySession(PasswordEncoder passwordEncoder, String code){
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("code", passwordEncoder.encode(code));

        return session;
    }
}
