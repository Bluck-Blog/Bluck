package com.choo.blog.domain.users.validator;


import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.dto.UserRegistData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserRegistDataValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistData registData = (UserRegistData) target;
        if(userRepository.existsByEmail(registData.getEmail())){
            errors.rejectValue("email", "duplication", "이미 존재하는 email 입니다.");
        }
    }
}
