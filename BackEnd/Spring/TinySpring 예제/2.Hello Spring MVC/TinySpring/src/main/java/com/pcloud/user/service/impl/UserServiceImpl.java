package com.pcloud.user.service.impl;

import com.pcloud.user.dao.UserDao;
import com.pcloud.user.dto.User;
import com.pcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;



    @Override
    public String FindID(String name, String phoneNum) {
        System.out.println("UserService: FindID Call");
        System.out.println("name:" + name);
        System.out.println("phoneNum:" + phoneNum);

        String id = dao.userSelect(name, phoneNum);
        return id;
    }

    @Override
    public User GetInfo(String name) {
        System.out.println("UserService: GetInfo Call");
        System.out.println("name:" + name);

        User user = dao.userSelect(name);
        return user;
    }

    @Override
    public void SetInfo(String name, int age) {
        System.out.println("UserService: SetInfo Call");
        System.out.println("name:" + name);
        System.out.println("age:" + age);

        dao.userUpdate(name, age);
    }
}
