package com.pcloud.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;

    public UserDto() {
    }

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
