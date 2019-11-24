package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // fetch 기본값은 eager, lazy로 설정하는 경우.. post데이터를 get하는 시정에 쿼리를 실행하 데이터를 가져온다.
    private Post post;

    private int up;
    private int down;
    private boolean best;

    private Integer likeCount;
}
