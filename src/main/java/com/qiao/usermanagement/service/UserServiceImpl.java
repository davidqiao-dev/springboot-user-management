package com.qiao.usermanagement.service;

import com.qiao.usermanagement.entity.UserEntity;
import com.qiao.usermanagement.exception.UserNotFoundException;
import com.qiao.usermanagement.mapper.UserMapper;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(Long id) {
        UserEntity entity = repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        return UserMapper.toModel(entity);
    }

    @Override
    public User createUser(User user) {

        UserEntity entity = repository.save(
                UserMapper.toEntity(user)
        );
        return UserMapper.toModel(entity);
    }

    @Override
    public User updateUser(Long id, User user) {
        getUser(id);

        UserEntity updateUser = UserMapper.toEntity(user);
        updateUser.setId(id);
        return UserMapper.toModel(repository.save(updateUser));
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);

        repository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toModel)
                .toList();
    }

}
