package com.example.redis.repository;

import com.example.redis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {
    @Insert("insert into mysql.REDIS_USER" +
            "(id,name,pwd,age) values (" +
            "#{id}," +
            "#{name}," +
            "#{pwd}," +
            "#{age}" +
            ")")
    void insertUser(User user);

    @Select("select * from mysql.REDIS_USER " +
            "where id = #{id}")
    Optional<User> findById(String id);
}
