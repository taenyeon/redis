package com.example.redis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pagination {
    private final int viewBoard = 10;

    private int page;

    private int endBoard;

    private int startBoard;

    public Pagination(int page){
        this.endBoard = viewBoard * page;
        this.startBoard = endBoard - 9;
    }
}
