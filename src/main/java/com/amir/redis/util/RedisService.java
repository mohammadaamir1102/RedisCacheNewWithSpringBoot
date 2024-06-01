package com.amir.redis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public <T> T getValueByKey(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            assert value != null;
            return objectMapper.readValue(value.toString(), type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ttl = time to live -> how many time data will be in cached it's optional
    public void setValueByKey(String key, Object object, Long ttl) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(object);
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
