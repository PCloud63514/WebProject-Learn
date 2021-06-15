package com.pcloud.userservice;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private Map<Long, User> users = new HashMap<>();
    private Long stock = 0L;
    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }

    @Override
    public User findByName(String name) {
        return users.values().stream().filter(u -> u.getName().equals(name)).findAny().get();
    }

    @Override
    public void add(String name) {
        users.put(stock, User.generate(stock, name));
        stock = stock + 1;
    }

    @Override
    public void remove(Long id) {
        users.remove(id);
    }
}
