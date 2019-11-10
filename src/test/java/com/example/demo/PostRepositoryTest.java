package com.example.demo;

import com.example.demo.repository.PostRepository;
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
        assertThat(post.getId()).isNull(); // 아직 데이터를 저장하지 않았기 때문에 pk(auto increment) 값이 존재하지 않아야함.

        Post dbInfo = postRepository.save(post);
        assertThat(dbInfo.getId()).isNotNull(); // 데이터 저장 후, pk값이 존재해야함.

        List<Post> dbInfoList = postRepository.findAll();
        assertThat(dbInfoList.size()).isEqualTo(1); // 전체 조회를 하면 1개의 데이터가 있어야함.
        assertThat(dbInfoList.contains(dbInfo)); // 조회된 한개의 데이터에는 위에서 저장 후 조회한 객체가 있어야함.


        Page<Post> postRepositoryAll = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(postRepositoryAll.getTotalElements()).isEqualTo(1); // 0페이지의 10개를 페이징해서 조회 했으므로 위에서 저장한 1개의 데이터가 존재해야함.
        assertThat(postRepositoryAll.getNumber()).isEqualTo(0); // 현재 페이지는 0임.
        assertThat(postRepositoryAll.getSize()).isEqualTo(10); // 페이징 사이즈는 10임.

        Page<Post> jpa = postRepository.findByTitleContains("JPA", PageRequest.of(0, 10));
        assertThat(jpa.getTotalElements()).isEqualTo(1);
        assertThat(jpa.getNumber()).isEqualTo(0);
        assertThat(jpa.getSize()).isEqualTo(10);

        Integer count = postRepository.countAllByTitle("KKK");
        assertThat(count).isEqualTo(0); // 해당하는 title로 저장한 데이터가 없기 때문에 조회된 갯수는 0이어야함.
    }

}