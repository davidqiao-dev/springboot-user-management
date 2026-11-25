package com.qiao.usermanagement.mapper;

import com.qiao.usermanagement.entity.UserEntity;
import com.qiao.usermanagement.model.User;

public class UserMapper {

    public static User toModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.name(),
                user.email(),
                user.age()
        );
    }
}
