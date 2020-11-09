package com.pcloud.user.service;

import com.pcloud.user.dto.User;

public interface UserService {
    public String FindID(String name, String phoneNum);
    public User GetInfo(String name);
    public void SetInfo(String name, int age);
}
