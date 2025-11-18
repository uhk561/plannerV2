package com.plannerv2.todo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponse {

    private final String title;
    private final String content;
    private final LocalDateTime modifiedAt;

    public UpdateTodoResponse( String title, String content, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}
