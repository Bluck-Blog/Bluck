package com.choo.blog.domain.users.service;

import com.choo.blog.domain.users.exceptions.DuplicateEmailException;
import com.choo.blog.domain.users.exceptions.InvalidVerifyCodeException;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String CODE_KEY = "code";

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final MailProvider mailProvider;

    public User join(UserRegistData registData){
        checkVerifyCode(registData.getVerifyCode());

        User user = modelMapper.map(registData, User.class);

        user.encrypte(passwordEncoder);

        return userRepository.save(user);
    }

    private void checkVerifyCode(String code) {
        Object sessionAttiribute = getSessionAttribute();

        if(sessionAttiribute == null){
            throw new InvalidVerifyCodeException();
        }

        String encryptCode = String.valueOf(sessionAttiribute);
        System.out.println("encryptCode = " + encryptCode);

        if(!passwordEncoder.matches(code, encryptCode)){
            throw new InvalidVerifyCodeException();
        }
    }

    private Object getSessionAttribute() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        Object sessionAttiribute = session.getAttribute(CODE_KEY);
        return sessionAttiribute;
    }

    public User getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return user;
    }

    public boolean verifyEmail(String rawCode, String code){
        return passwordEncoder.matches(rawCode, code);
    }

    public String generateVerifyCode(String email){
        checkDuplicate(email);
        String code = VerifyCodeUtil.generateToken();
        sendEmailVerifyMail(email, code);
        return passwordEncoder.encode(code);
    }

    private void checkDuplicate(String email) {
        boolean existEmail = userRepository.existsByEmail(email);
        if(existEmail){
            throw new DuplicateEmailException(email);
        }
    }

    private void sendEmailVerifyMail(String email, String code){

        Map<String,Object> attributes = new HashMap<>();
        attributes.put("code", code);

        MailMessage mailMessage = MailMessage.builder()
                .templateName("verify")
                .to(new String[]{email})
                .subject("BLUCK ????????????")
                .attributes(attributes)
                .build();
        System.out.println("mailMessage = " + mailMessage);
        mailProvider.send(mailMessage);
    }
}
