package com.example.redis.controller;

import com.example.redis.domain.Board;
import com.example.redis.domain.Pagination;
import com.example.redis.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("")
    public List<Board> findBoards(@RequestParam(defaultValue = "1",required = false) int page){
        return boardService.findAll(page);
    }
    @GetMapping("/{id}")
    public Board findBoard(@PathVariable String id){
       return boardService.findById(id);
    }
    @PostMapping("")
    public void createTestBoards(){
        boardService.insertBoard();
    }
}
