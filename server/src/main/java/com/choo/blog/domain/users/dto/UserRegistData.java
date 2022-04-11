package com.choo.blog.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistData {
    @NotEmpty
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}",
            message = "비밀번호는 영어와 숫자,특수문자(!@#$%^&*)를 포함해서 8~20자리 이내로 입력하세요.")
    private String password;

    @NotEmpty
    private String nickname;

    private String image;

    private LocalDate birthdate;

    @NotEmpty
    private String description;
}
