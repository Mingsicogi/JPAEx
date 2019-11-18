package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Post2;
import com.example.demo.repository.Post2Repository;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Slf4j
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Autowired
    Post2Repository post2Repository;

    @Test
    void getPost() throws Exception{

        Post post = new Post();
        post.setTitle("JPA");

        Post save = postRepository.save(post);

        MvcResult mvcResult = mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


    }

    @Test
    void getPosts() throws Exception {
        Post2 post = new Post2();
        post.setTitle("JPA");

//        Comment comment = new Comment();
//        comment.setContent("b.b");
//
//        post.getComments().add(comment);

        post2Repository.save(post);
//        postRepository.flush();

        mockMvc.perform
                (
                    get("/posts")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,DESC")
                        .param("sort", "title")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}