package com.example.demo;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {


    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void test(){
        Comment comment = new Comment();
        comment.setContent("TEST");
        comment.setUp(5);
        commentRepository.save(comment);

        Post post = new Post();
        post.setTitle("POST");
        comment.setPost(post);

    }
}
