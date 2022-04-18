package com.example.redis.repository;

import com.example.redis.domain.Board;
import com.example.redis.domain.Pagination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    @Insert("insert into mysql.redis_board (title, content) VALUES (#{title},#{content})")
    void insertBoard(Board board);

    @Select("select * from mysql.redis_board order by id limit #{startBoard},#{endBoard}")
    List<Board> findAll(Pagination pagination);

    @Select("select * from mysql.redis_board where id = #{id}")
    Optional<Board> findById(String id);
}
