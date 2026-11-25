package com.qiao.usermanagement.service.impl;

import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Long id) {
        return new User(
                id,
                "Davi",
                "david@example.com",
                45
        );
    }

    @Override
    public User createUser(User user) {
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        return new User(
                id,
                user.name(),
                user.email(),
                user.age()
        );
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<User> getUsers() {
        return List.of(
                new User(1L,
                        "David",
                        "david@example.com",
                        45
                ),
                new User(2L,
                        "Tom",
                        "tom@example.com",
                        30
                )
        );
    }

}
