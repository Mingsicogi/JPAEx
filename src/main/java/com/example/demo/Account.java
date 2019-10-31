package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity // db에 Account table과 매핑이 되는 것을 나타내는 annotation. hibernate 에서 사용하는 매핑명
@Table(name = "Account")// db에 생성되는 테이블 명 정의
public class Account {

    @Id // pk
    @GeneratedValue // auto increment
    private Long id;

    @Column(nullable = false, unique = true) // 모든 필드에 생략되어 있는 annotation
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient // 필드로만 사용. jpa에는 사용되지 않음.
    private String no;
}
