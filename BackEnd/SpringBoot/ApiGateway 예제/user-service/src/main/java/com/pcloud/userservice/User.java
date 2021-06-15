package com.pcloud.userservice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class User {
    private Long id;
    private String name;


    public static User generate(Long id, String name) {
        return new UserBuilder().id(id).name(name).build();
    }
}
