package com.example.redis.service;

import com.example.redis.domain.Board;
import com.example.redis.domain.Pagination;
import com.example.redis.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void insertBoard(){
        int i=0;
        while (i<100){
            Board board = Board.builder().
                    title("test" + i).
                    content("testContent" + i).
                    build();
            boardRepository.insertBoard(board);
            i++;
            log.info("Insert Board - count : {}",i);
        }
    }
    @Cacheable(key = "#page",value = "findAll")
    public List<Board> findAll(int page){
        Pagination pagination = new Pagination(page);
        List<Board> boards = boardRepository.findAll(pagination);
        log.info("Find All - page : {}",page);
        return boards;
    }
    @Cacheable(key = "#id",value = "findById")
    public Board findById(String id){
        return boardRepository.
                findById(id).
                orElseThrow(()-> new IllegalStateException("번호에 해당하는 게시물을 찾을 수 없음."));
    }
}
