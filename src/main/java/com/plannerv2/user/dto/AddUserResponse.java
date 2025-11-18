package com.plannerv2.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddUserResponse {

    private final Long id;
    private String name;
    private String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public AddUserResponse(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt)  {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
