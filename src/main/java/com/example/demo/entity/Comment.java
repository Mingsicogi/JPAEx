package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne // fetch 기본값은 eager
    private Post post;

    private Integer likeCount;
}
