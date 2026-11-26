package com.qiao.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootUserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserManagementApplication.class, args);
    }

}
