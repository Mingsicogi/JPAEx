package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Post2;
import com.example.demo.repository.Post2Repository;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hateoas/posts")
    public PagedModel<EntityModel<Post2>> getPostsAsHateoas(Pageable Pageable, PagedResourcesAssembler<Post2> resourcesAssembler) {
        return resourcesAssembler.toModel(post2Repository.findAll(Pageable));
    }
}
