package com.plannerv2.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {

    private final String name;
    private final LocalDateTime modifiedAt;

    public UpdateUserResponse(String name, LocalDateTime modifiedAt) {
        this.name = name;
        this.modifiedAt = modifiedAt;
    }
}
