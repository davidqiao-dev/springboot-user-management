package com.qiao.usermanagement.controller;

import com.qiao.usermanagement.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(
                id,
                "David",
                "david@example.com",
                45
        );
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }
}
