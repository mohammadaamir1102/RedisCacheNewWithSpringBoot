package com.amir.redis.service.impl;

import com.amir.redis.entity.User;
import com.amir.redis.repo.UserRepository;
import com.amir.redis.res.UserResponse;
import com.amir.redis.util.CommonUtil;
import com.amir.redis.util.RedisService;
import com.amir.redis.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String KEY = "USER_";

    @Autowired
    private RedisService redisService;

    @Override
    public UserResponse saveUser(User user) throws JsonProcessingException {
        user.setAge(CommonUtil.calculateAge(user.getDob().toString()));
        User savedUser = userRepository.save(user);
        redisService.setValueByKey(KEY + savedUser.getId().toString(), savedUser, 300l);
        return UserResponse.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .dob(savedUser.getDob())
                .age(savedUser.getAge()).build();
    }


    @Override
    public UserResponse getUserById(Long id) {
        User savedUser = redisService.getValueByKey(KEY + id.toString(), User.class);
        if (savedUser != null) {
            return UserResponse.builder()
                    .id(savedUser.getId())
                    .fullName(savedUser.getFullName())
                    .email(savedUser.getEmail())
                    .dob(savedUser.getDob())
                    .age(savedUser.getAge()).build();
        }
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserResponse.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .dob(user.getDob())
                    .age(user.getAge()).build();
        }
        return null;
    }
}
