package com.choo.blog.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserLoginData {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
