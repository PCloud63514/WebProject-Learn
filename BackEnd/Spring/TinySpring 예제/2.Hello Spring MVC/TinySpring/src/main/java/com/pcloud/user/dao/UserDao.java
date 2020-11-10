package com.pcloud.user.dao;

import com.pcloud.user.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {

    public UserDto userSelect(String name, String phoneNUm) {
        UserDto userDto = new UserDto();
        userDto.setId("test123");
        userDto.setName("pcloud");
        userDto.setPhoneNum("000-0000-0000");
        userDto.setAge(99);

        return userDto;
    }

    public UserDto userSelect(String name) {
        UserDto userDto = new UserDto();
        userDto.setId("test123");
        userDto.setName("pcloud");
        userDto.setPhoneNum("000-0000-0000");
        userDto.setAge(99);

        return userDto;
    }

    public void userUpdate(String name, int age) {

    }
}
