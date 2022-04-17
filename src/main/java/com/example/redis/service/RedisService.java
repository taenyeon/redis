package com.example.redis.service;

import com.example.redis.domain.User;
import com.example.redis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RedisService {
    private final RedisTemplate<String,Object> redisTemplate;

    public RedisService(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void getRedisStringValue(String key) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        log.info("GET - Redis key : {}", key);
        log.info("GET - Redis value : {}", value);
    }

    public void setRedisStringValue(String key, String value){
        ValueOperations<String,Object> stringStringValueOperations =
                redisTemplate.opsForValue();
        stringStringValueOperations.set(key,value);
        log.info("SET - Redis key : {}", key);
        log.info("SET - Redis value : {}", value);
    }

    public void setRedisUserValue(User user){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        if (getRedisUserValue(user.getId()) == null){
        valueOperations.set(user.getId(),user);
        log.info("User Session Add - id : {}",user.getId());
        }
    }

    public User getRedisUserValue(String id){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        log.info("User Session Get - id : {}",id);
         return (User) valueOperations.get(id);
    }

    public void delRedisUserValue(String id){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.getOperations().delete(id);
        log.info("User Session Del - id : {}",id);
    }

}
