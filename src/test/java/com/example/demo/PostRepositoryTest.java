package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest // test이기 때문에 @transaction 이 있음
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Rollback(false) // 해당 어노테이션을 붙이지 않으면 insert query를 실행하지 않음. -> hibernate가 롤백될것을 알고 insert쿼리를 실해하지 않음.
    public void crudTest(){
        Post post = new Post();
        post.setTitle("JPA Repository Test!!");
        assertThat(post.getId()).isNull();

        Post dbInfo = postRepository.save(post);

        assertThat(dbInfo.getId()).isNotNull();

        List<Post> dbInfoList = postRepository.findAll();
        assertThat(dbInfoList.size()).isEqualTo(1);
        assertThat(dbInfoList.contains(dbInfo));


        Page<Post> postRepositoryAll = postRepository.findAll(PageRequest.of(0, 10));

        assertThat(postRepositoryAll.getTotalElements()).isEqualTo(1);
        assertThat(postRepositoryAll.getNumber()).isEqualTo(0);
        assertThat(postRepositoryAll.getSize()).isEqualTo(10);

        Page<Post> jpa = postRepository.findByTitleContains("JPA", PageRequest.of(0, 10));
        assertThat(jpa.getTotalElements()).isEqualTo(1);
        assertThat(jpa.getNumber()).isEqualTo(0);
        assertThat(jpa.getSize()).isEqualTo(10);

        Integer count = postRepository.countAllByTitle("KKK");
        assertThat(count).isEqualTo(0);
    }

}