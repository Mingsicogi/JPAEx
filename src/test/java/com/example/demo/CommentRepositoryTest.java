package com.example.demo;

import com.example.demo.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void crud(){
        Comment comment = new Comment();
        comment.setContent("HELLO CUSTOM JPA REPOSITORY");
        assertThat(comment.getId()).isNull();

        Comment dbInfo = commentRepository.save(comment);
        assertThat(dbInfo.getId()).isNotNull();

        List<Comment> dbInfoList = commentRepository.findAll();
        assertThat(dbInfoList.size()).isEqualTo(1);
    }
}