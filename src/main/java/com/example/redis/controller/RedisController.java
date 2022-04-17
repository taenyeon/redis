package com.example.redis.controller;

import com.example.redis.domain.User;
import com.example.redis.service.RedisService;
import com.example.redis.service.UserService;
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
    private final UserService userService;

    public RedisController(RedisService redisService, UserService userService) {
        this.redisService = redisService;
        this.userService = userService;
    }

    @PostMapping(value = "/setRedis")
    public void setRedis(String key, String value){
        redisService.setRedisStringValue(key,value);
    }

    @GetMapping(value = "/getRedis/{key}")
    public void getRedis(@PathVariable String key){
        redisService.getRedisStringValue(key);
    }

    @PostMapping(value = "/register")
    public void register(User user){
        userService.register(user);
    }

    @PostMapping(value = "/login")
    public User login(String id, String pwd, HttpSession session){
        User user = userService.login(id, pwd);
        session.setAttribute("id",user.getId());
        return user;
    }

    @GetMapping(value = "/logout")
    public void logout(HttpSession session){
        String id = (String) session.getAttribute("id");
        session.removeAttribute(id);
        userService.logout(id);
    }

    @GetMapping(value = "/userInfo")
    public User userInfo(HttpSession session){
        String id = (String) session.getAttribute("id");
        return userService.userInfo(id);
    }
}
