package com.plannerv2.todo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoRequest {

    private String title;
    private String content;
}
