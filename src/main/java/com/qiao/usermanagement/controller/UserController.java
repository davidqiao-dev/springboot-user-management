package com.qiao.usermanagement.controller;

import com.qiao.usermanagement.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public User createUser(@Valid @RequestBody User user) {
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@Valid @PathVariable Long id, @RequestBody User user) {
        return new User(
                id,
                user.name(),
                user.email(),
                user.age()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        return "User " + id + " deleted successfully";
        return ResponseEntity.noContent().build();
    }
}
