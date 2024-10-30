package com.amir.redis.controller;

import com.amir.redis.entity.User;
import com.amir.redis.service.UserService;
import com.amir.redis.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser;
        try {
            savedUser = userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user;
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{key}")
    public String deleteByKey(@PathVariable String key){
        boolean isDeleted = redisService.deleteFromRedisCache(key);
        return  isDeleted ? key +" is deleted successfully" : "key not found";
    }

    @DeleteMapping("/delete")
    public String deleteMultipleByKeys(@RequestBody List<String> keys){
        long deletedCount = redisService.deleteMultipleFromRedisCache(keys);
        return  deletedCount > 0 ? " records deleted successfully" : "key not found";
    }

}
