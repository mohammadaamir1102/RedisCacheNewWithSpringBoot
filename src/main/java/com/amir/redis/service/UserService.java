package com.amir.redis.service;

import com.amir.redis.entity.User;
import com.amir.redis.res.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {
    UserResponse saveUser(User user) throws JsonProcessingException;

    UserResponse getUserById(Long id);
}
