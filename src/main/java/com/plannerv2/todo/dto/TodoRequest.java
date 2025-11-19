package com.plannerv2.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequest {


    @NotBlank(message = "제목은 필수값")
    @Size(max = 30)
    private String title;

    @NotBlank(message = "내용은 필수값")
    @Size(max = 300)
    private String content;
}
