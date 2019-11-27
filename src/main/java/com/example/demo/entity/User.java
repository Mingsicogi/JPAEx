package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class User {

    private Long id;

    private String username;

    private String password;
}
