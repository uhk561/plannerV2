package com.plannerv2.todo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponse {

    private final Long Id;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateTodoResponse(Long id, String title, String content, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        Id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
