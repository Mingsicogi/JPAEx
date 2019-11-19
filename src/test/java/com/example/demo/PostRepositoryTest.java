package com.example.demo;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // test이기 때문에 @transaction 이 있음
@Slf4j
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    /**
     * 객체의 상태 변화를 알고 코딩하는 것이 중요함.
     *
     * save 후에 리턴하는 값은 항상 영속화된 값을 리턴함.
     *
     *
     */
    @Test
    public void save(){

        // transient 상태 - hibernate, jpa 아무도 모르는 상태
        Post post = new Post();
        post.setTitle("minseok");

        // Persistent 상태 DB에 저장되고 cache된 상태, Hibernate가 알고 있는 상태
        Post savedPost = postRepository.save(post); // persist 로 가는 상태일땐 전달한 객체가 영속화가 됨.
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(entityManager.contains(post)).isTrue();
        assertThat(post == savedPost).isTrue();
        assertThat(post.getId()).isEqualTo(1L);

        // Detached 상태 - 한번이라도 Persistent가 됬던 상태
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle(("minssogi"));
        Post savedPost2 = postRepository.save(post1);// merge, 복사본을 만들어 영속화를 하고, 전달한 객체는 다시 리턴함. 즉 영속화 되지 않음.
        assertThat(entityManager.contains(savedPost2)).isTrue();
        assertThat(entityManager.contains(post1)).isFalse();
        assertThat(post1 != savedPost2).isTrue();

        savedPost2.setTitle("JEON");

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
        all.forEach(System.out::println);

    }
}