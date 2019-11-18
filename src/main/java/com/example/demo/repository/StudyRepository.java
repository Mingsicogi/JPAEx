package com.example.demo.repository;

import com.example.demo.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StudyRepository extends JpaRepository<Study, Long>, QuerydslPredicateExecutor<Study> {

}
