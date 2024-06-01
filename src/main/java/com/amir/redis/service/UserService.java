package com.amir.redis.service;

import com.amir.redis.entity.User;

public interface UserService {
    User saveUser(User user);

    User getUserById(Long id);
}
