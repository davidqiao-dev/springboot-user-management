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

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return new User(
                id,
                user.name(),
                user.email(),
                user.age()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User " + id + " deleted successfully";
    }
}
