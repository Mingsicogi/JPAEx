package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.stream.Stream;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long>, QuerydslPredicateExecutor<CommentRepository> {

    // 기본 메소드는 공통 interface로 처리함.
//    Comment save(Comment comment);
//
//    List<Comment> findAll();

    // 사용자 정의 쿼리
    Page<Comment> findByContentContainsAndPost(String content, Post post, Pageable pageable);

    List<Comment> findByContentContains(String keyword);

    List<Comment> findByContentContainsIgnoreCase(String keyword);

    List<Comment> findByContentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, Integer likeCount);

//    List<Comment> findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc(String keyword, Integer likeCount);

    List<Comment> findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountAsc(String keyword, Integer i);

    Page<Comment> findByContentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, Integer i, Pageable pageable);

    Stream<Comment> findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc(String keyword, Integer i);
}
