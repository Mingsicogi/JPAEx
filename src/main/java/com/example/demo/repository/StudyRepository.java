package com.example.demo.repository;

import com.example.demo.entity.Study;
import com.example.demo.repository.common.MySimpleRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StudyRepository extends MySimpleRepository<Study, Long>, QuerydslPredicateExecutor<Study> {

}
