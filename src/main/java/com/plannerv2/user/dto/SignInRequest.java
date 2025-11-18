package com.plannerv2.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignInRequest {

    @NotBlank
    @Email(message="올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message="비밀번호는 필수 값")
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,20}$", message = "영문, 숫자, 특수문자를 포함해 15자 이내")
    private String password;
}
