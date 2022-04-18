package com.example.redis.service;

import com.example.redis.domain.User;
import com.example.redis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;

    public UserService(UserRepository userRepository, RedisService redisService) {
        this.userRepository = userRepository;
        this.redisService = redisService;
    }

    public void register(User user){
        log.info("InsertUser Start - userId : {}",user.getId());
        userRepository.insertUser(user);
        log.info("Insert Success");
    }

    public User login(String id, String pwd){
        User user;
            user = userRepository.findById(id).
                    orElseThrow(()-> new IllegalStateException("회원 정보를 찾을 수 없음."));
        if(!user.getPwd().equals(pwd)){
            throw new IllegalStateException("비밀번호 불일치");
        }

        redisService.setRedisUserValue(user);

        log.info("login Success - id : {}",id);
        return user;
    }

    public void logout(String id){
        redisService.delRedisUserValue(id);
        log.info("logout Success - id : {}",id);
    }

    public User userInfo(String id){
        return redisService.getRedisUserValue(id);
    }
}
