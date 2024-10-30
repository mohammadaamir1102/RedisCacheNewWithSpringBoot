//package com.amir.redis.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class RedisService {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//    private static final String KEY = "USER_";
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    public <T> T getValueByKey(String key, Class<T> type) {
//        Object value = redisTemplate.opsForValue().get(key);
//        try {
//            return value != null ? objectMapper.readValue(value.toString(), type) : null;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // ttl = time to live -> how many time data will be in cached it's optional
//    public void setValueByKey(String key, Object object, Long ttl) throws JsonProcessingException {
//            String value = objectMapper.writeValueAsString(object);
//            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
//
//    }
//
//    public boolean deleteFromRedisCache(String key){
//        return Boolean.TRUE.equals(redisTemplate.delete(KEY + key));
//    }
//
//    public long deleteMultipleFromRedisCache(List<String> keys){
//        List<String> listOfKeys = keys.stream().map(item -> KEY + item).toList();
//        Long delete = redisTemplate.delete(listOfKeys);
//        return delete == null ? 0 : delete;
//    }
//}
