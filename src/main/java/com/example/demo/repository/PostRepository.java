package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    Integer countAllByTitle(String title);

    @Async
    Future<List<Post>> findAllByTitleContains(String title);


    List<Post> findByTitleStartingWith(String title);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1"/*, nativeQuery = true*/)
    List<Post> findByTitle(String title);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1"/*, nativeQuery = true*/)
    List<Post> findByTitleSortTitle(String title, Sort sort);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1"/*, nativeQuery = true*/)
    List<Post> findByTitleSortTitleOfLength(String title, JpaSort sort);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = :title"/*, nativeQuery = true*/)
    List<Post> findByTitleNamedParameter(@Param("title") String title);

    @Query(value = "SELECT p FROM #{#entityName} AS p WHERE p.title = :title"/*, nativeQuery = true*/)
    List<Post> findByTitleSpEL(@Param("title") String title);
}
