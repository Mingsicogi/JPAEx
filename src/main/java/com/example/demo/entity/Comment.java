package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // fetch 기본값은 eager, lazy로 설정하는 경우.. post데이터를 get하는 시정에 쿼리를 실행하 데이터를 가져온다.
    private Post post;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    @CreatedBy
    @ManyToOne
    private User creator;

    @LastModifiedBy
    private User modifier;

    private int up;
    private int down;
    private boolean best;

    private Integer likeCount;

    @PrePersist
    public void prePersist(){
        System.out.println("========goood========");
    }
}
