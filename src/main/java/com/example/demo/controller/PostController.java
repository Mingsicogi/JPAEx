package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Post2;
import com.example.demo.repository.Post2Repository;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Post2Repository post2Repository;

    /**
     * domain converter
     *
     * @param id
     * @return
     */
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable("id") Post post){
//        Optional<Post> byId = postRepository.findById(id);
//        return byId.orElse(new Post());

        return post;
    }

    @GetMapping("/posts")
    public Page<Post2> getPosts(Pageable Pageable) {
        return post2Repository.findAll(Pageable);
    }
}
