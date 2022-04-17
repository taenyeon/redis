package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void getRedisStringValue(String key) {
        ValueOperations<String, String> stringStringValueOperations =
                stringRedisTemplate.opsForValue();
        String value = stringStringValueOperations.get(key);
        log.info("GET - Redis key : {}", key);
        log.info("GET - Redis value : {}", value);
    }

    public void setRedisStringValue(String key, String value){
        ValueOperations<String, String> stringStringValueOperations =
                stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(key,value);
        log.info("SET - Redis key : {}", key);
        log.info("SET - Redis value : {}", value);
    }
}
