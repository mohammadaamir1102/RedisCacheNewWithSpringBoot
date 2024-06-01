package com.amir.redis.service.impl;

import com.amir.redis.entity.User;
import com.amir.redis.repo.UserRepository;
import com.amir.redis.util.RedisService;
import com.amir.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String KEY = "USER";

    @Autowired
    private RedisService redisService;

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        redisService.setValueByKey(KEY + savedUser.getId().toString(), savedUser, 300l);
        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        User cacheUserResponse = redisService.getValueByKey(KEY + id.toString(), User.class);
        if (cacheUserResponse != null) {
            return cacheUserResponse;
        }
        return userRepository.findById(id).orElse(null);
    }
}
