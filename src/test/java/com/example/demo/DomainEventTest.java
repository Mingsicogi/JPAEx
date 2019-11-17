package com.example.demo;

import com.example.demo.config.TestConfig;
import com.example.demo.entity.Post;
import com.example.demo.event.PostPublishEvent;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class)
@Slf4j
public class DomainEventTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void event(){
        Post post = new Post();
        post.setTitle("Test Domain Event!!");

        PostPublishEvent postPublishEvent = new PostPublishEvent(post);

        applicationContext.publishEvent(postPublishEvent); // spring, application context에는 event 기능이 존재함.
    }

    @Test
    public void crud(){
        String newTitle = "Test Domain Event!!";

        Post post = new Post();
        post.setTitle(newTitle);

//        postRepository.save(post);
        postRepository.save(post.publish());

        assertThat(postRepository.findByTitleContains(newTitle, PageRequest.of(0, 10)).getNumberOfElements()).isEqualTo(1);

        postRepository.deleteAll();
        postRepository.flush();

    }
}
