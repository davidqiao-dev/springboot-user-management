package com.qiao.usermanagement.service;

import com.qiao.usermanagement.entity.UserEntity;
import com.qiao.usermanagement.exception.UserNotFoundException;
import com.qiao.usermanagement.mapper.UserMapper;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {

        System.out.println("loading user from database...");
        log.info("loading user {} from database...", id);

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
    @CachePut(value = "users", key = "#id")
    public User updateUser(Long id, User user) {

        UserEntity updateUser = repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        updateUser.setName(user.name());
        updateUser.setEmail(user.email());
        updateUser.setAge(user.age());

        return UserMapper.toModel(repository.save(updateUser));
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        repository.deleteById(id);
    }

    @Override
    public Page<User> getUsers(
            int page
            , int size
            , String sortBy
            , String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable)
                .map(UserMapper::toModel)
                ;
    }

}
