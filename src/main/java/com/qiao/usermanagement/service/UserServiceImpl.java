package com.qiao.usermanagement.service;

import com.qiao.usermanagement.exception.UserNotFoundException;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

    @Override
    public User createUser(User user) {

        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        getUser(id);

        User updateUser = new User(id,
                user.name(),
                user.email(),
                user.age());
        return repository.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);

        repository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

}
