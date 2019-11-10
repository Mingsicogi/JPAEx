package com.example.demo.repository;

import com.example.demo.Comment;
import com.example.demo.repository.common.CommonRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends CommonRepository<Comment, Long> {

//    Comment save(Comment comment);
//
//    List<Comment> findAll();
}
