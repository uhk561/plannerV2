package com.plannerv2.todo.dto;

import lombok.Getter;

@Getter
public class CreateTodoRequest {

    private Long userId;
    private String title;
    private String content;
}
