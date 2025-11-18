package com.plannerv2.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank(message="이름은 필수 값")
    @Size(max = 30)
    private String name;

}
