package com.qiao.usermanagement.model;

public record User(
        Long id,
        String name,
        String email,
        Integer age
) {
}