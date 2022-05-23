package com.choo.blog.domain.users.service;

import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.User;
import com.choo.blog.exceptions.UserNotFoundException;
import com.choo.blog.mail.MailMessage;
import com.choo.blog.mail.MailProvider;
import com.choo.blog.util.VerifyCodeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final MailProvider mailProvider;

    public User join(UserRegistData registData){
        User user = modelMapper.map(registData, User.class);

        user.encrypte(passwordEncoder);

        return userRepository.save(user);
    }

    public User getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return user;
    }

    public String generateVerifyCode(String email){
        String code = VerifyCodeUtil.generateToken();
        sentRegistationMail(email, code);
        return code;
    }

    public void sentRegistationMail(String email, String code){

        Map<String,Object> attributes = new HashMap<>();
        attributes.put("code", code);

        MailMessage mailMessage = MailMessage.builder()
                .templateName("verity")
                .to(new String[]{email})
                .subject("BLUCK 인증번호")
                .attributes(attributes)
                .build();
        mailProvider.send(mailMessage);
    }
}
