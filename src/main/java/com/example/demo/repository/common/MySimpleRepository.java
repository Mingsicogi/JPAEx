package com.example.demo.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface MySimpleRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    boolean contains(T entity);
}
