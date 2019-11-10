package com.example.demo.repository;

import com.example.demo.Comment;
import com.example.demo.Post;
import com.example.demo.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

    // 기본 메소드는 공통 interface로 처리함.
//    Comment save(Comment comment);
//
//    List<Comment> findAll();

    // 사용자 정의 쿼리
    Page<Comment> findByContentContainsAndPost(String content, Post post, Pageable pageable);
}
