package com.choo.blog.domain.users.controller;

import com.choo.blog.commons.enums.EnumType;
import com.choo.blog.commons.response.ApiResponse;
import com.choo.blog.domain.posts.enums.PostOpenType;
import com.choo.blog.domain.users.dto.SessionResponseData;
import com.choo.blog.domain.users.dto.UserLoginData;
import com.choo.blog.domain.users.exceptions.CodeNotFoundException;
import com.choo.blog.domain.users.service.AuthenticationService;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.exceptions.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/session", produces = MediaTypes.HAL_JSON_VALUE)
public class SessionController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse login(@Valid @RequestBody UserLoginData loginData, BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }

        String accessToken = authenticationService.login(loginData);
        SessionResponseData sess = SessionResponseData.builder()
                .accessToken(accessToken)
                .build();
        return ApiResponse.status(HttpStatus.CREATED).body(sess);
    }

    @GetMapping("/verify")
    public ResponseEntity generateVerifyCode(String email){

        String code = userService.generateVerifyCode(email);


        return ApiResponse.status(HttpStatus.CREATED).body(code).toResponse();
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(String verifyCode, String code){

        boolean result = userService.verifyEmail(verifyCode, code);

        return ApiResponse.status(HttpStatus.OK).body(result).toResponse();
    }
}
