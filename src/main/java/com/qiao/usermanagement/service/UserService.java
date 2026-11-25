package com.qiao.usermanagement.service;

import com.qiao.usermanagement.model.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getUsers();
}
