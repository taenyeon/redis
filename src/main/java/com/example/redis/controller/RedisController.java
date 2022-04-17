package com.example.redis.controller;

import com.example.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping(value = "/setRedis")
    public void setRedis(String key, String value){
        redisService.setRedisStringValue(key,value);
    }
    @GetMapping(value = "/getRedis/{key}")
    public void getRedis(@PathVariable String key){
        redisService.getRedisStringValue(key);
    }
}
