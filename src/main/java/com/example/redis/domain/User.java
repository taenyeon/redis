package com.example.redis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RedisHash(value = "user", timeToLive = 30)
public class User {

    private String id;
    private String name;
    private String pwd;
    private Integer age;
    private LocalDateTime createAt;
}
