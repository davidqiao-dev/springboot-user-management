package com.qiao.usermanagement.controller;

import com.qiao.usermanagement.common.ApiResponse;
import com.qiao.usermanagement.model.User;
import com.qiao.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUser(@PathVariable Long id) {

        return ApiResponse.success(
                userService.getUser(id)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {

        User createdUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "User created successfully",
                                createdUser)
                );
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@Valid @PathVariable Long id,
                                        @RequestBody User user) {

        return ApiResponse.success(
                "User updated successfully",
                userService.updateUser(id, user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {

        return userService.getUsers(page, size);
    }
}
