package com.pcloud.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinUserDto {
    private String name;

    public JoinUserDto() {
    }

    public JoinUserDto(String name) {
        this.name = name;
    }
}
