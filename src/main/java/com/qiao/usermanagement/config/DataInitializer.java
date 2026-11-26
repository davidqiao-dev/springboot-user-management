package com.qiao.usermanagement.config;

import com.qiao.usermanagement.mapper.UserMapper;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("david@example.com")) {
            userRepository.save(
                    UserMapper.toEntity(new User(
                                    null,
                                    "David",
                                    "david@example.com",
                                    45
                            )
                    )
            );
        }

        if (!userRepository.existsByEmail("tom@example.com")) {
            userRepository.save(
                    UserMapper.toEntity(new User(
                                    null,
                                    "Tom",
                                    "tom@example.com",
                                    30
                            )
                    )
            );
        }

    }

}
