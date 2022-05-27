package com.choo.blog.domain.users.controller;

import com.choo.blog.commons.response.ApiResponse;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/session", produces = MediaTypes.HAL_JSON_VALUE)
public class SessionController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseData login(@Valid @RequestBody UserLoginData loginData, BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }

        String accessToken = authenticationService.login(loginData);
        return SessionResponseData.builder()
                .accessToken(accessToken)
                .build();
    }

    @GetMapping("/verify")
    public ResponseEntity generateVerifyCode(String email, HttpServletRequest request){
        HttpSession session = request.getSession();
        String code = userService.generateVerifyCode(email);
        session.setAttribute("code", code);

        return ApiResponse.status(HttpStatus.CREATED).body(code).toResponse();
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(String code, HttpServletRequest request){
        HttpSession session = request.getSession();
        Object sessionCode = session.getAttribute("code");
        if(sessionCode == null){
            throw new CodeNotFoundException();
        }
        String encodedCode = String.valueOf(sessionCode);

        boolean result = userService.verifyEmail(code, encodedCode);

        return ApiResponse.status(HttpStatus.OK).body(result).toResponse();
    }
}
