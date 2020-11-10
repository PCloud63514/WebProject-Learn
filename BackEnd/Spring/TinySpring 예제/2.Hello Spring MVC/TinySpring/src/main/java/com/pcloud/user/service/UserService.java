package com.pcloud.user.service;

import com.pcloud.user.dto.UserDto;

public interface UserService {
    public String FindID(String name, String phoneNum);
    public UserDto GetInfo(String name);
    public void SetInfo(String name, int age);
}
