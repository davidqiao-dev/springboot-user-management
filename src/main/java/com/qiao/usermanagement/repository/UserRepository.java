package com.qiao.usermanagement.repository;

import com.qiao.usermanagement.entity.UserEntity;
import com.qiao.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



}
