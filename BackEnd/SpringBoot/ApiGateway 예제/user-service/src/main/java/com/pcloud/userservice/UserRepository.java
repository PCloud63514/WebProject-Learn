package com.pcloud.userservice;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User findById(Long id);
    User findByName(String name);
    void add(String name);
    void remove(Long id);
}
