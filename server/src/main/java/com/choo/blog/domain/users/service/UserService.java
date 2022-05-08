package com.choo.blog.domain.users.service;

import com.choo.blog.domain.users.repository.UserRepository;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.User;
import com.choo.blog.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

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
}
