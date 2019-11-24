package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Comment_;
import org.springframework.data.jpa.domain.Specification;

/**
 * jpa specification
 *
 *
 * @author minssogi
 */
public class CommentSpecs {

    public static Specification<Comment> isBest(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(Comment_.best));
    }

    public static Specification<Comment> isGood(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Comment_.UP), 10);
    }
}
