package com.example.redis.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
}
