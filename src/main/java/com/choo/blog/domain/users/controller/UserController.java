package com.choo.blog.domain.users.controller;

import com.choo.blog.commons.response.ApiResponse;
import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.UserModel;
import com.choo.blog.domain.users.dto.UserRegistData;
import com.choo.blog.domain.users.service.UserService;
import com.choo.blog.domain.users.validator.UserRegistDataValidator;
import com.choo.blog.exceptions.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserRegistDataValidator userRegistDataValidator;

    @InitBinder
    public void registDataInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userRegistDataValidator);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserModel>> registUser(@RequestBody @Valid UserRegistData registData, BindingResult result){
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }

        User user = userService.join(registData);
        UserModel userModel = new UserModel(user);

        return ApiResponse.status(HttpStatus.CREATED).body(userModel).toResponse();
    }
}
