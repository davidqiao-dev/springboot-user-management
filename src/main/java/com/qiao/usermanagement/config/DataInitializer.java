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

        userRepository.save(
                UserMapper.toEntity(new User(
                                1L,
                                "David",
                                "david@example.com",
                                45
                        )
                )
        );

        userRepository.save(
                UserMapper.toEntity(new User(
                                2L,
                                "Tom",
                                "tom@example.com",
                                30
                        )
                )
        );
    }

}
