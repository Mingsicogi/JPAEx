package com.example.demo;

import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@Slf4j
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Before
    public void testDataInitialize() {
        log.info("##### Test Ready Start #####");
        Post post = new Post();
        post.setTitle("HI JPA!");


        Comment comment = new Comment();
        comment.setContent("Nice to meet you");

        post.addComment(comment); // 관계 설정.

        Post postDbInfo = postRepository.save(post);
        assertThat(postDbInfo).isNotNull();

        log.info("##### Test Ready Finish #####");
    }

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setContent("HELLO CUSTOM JPA REPOSITORY");
        assertThat(comment.getId()).isNull();

        Comment dbInfo = commentRepository.save(comment);
        assertThat(dbInfo.getId()).isNotNull();

        List<Comment> dbInfoList = commentRepository.findAll();
        assertThat(dbInfoList.size()).isEqualTo(1);

        Optional<Comment> optionalComment = commentRepository.findById(2L);
        assertThat(optionalComment.isPresent()).isFalse();


        commentRepository.save(null);

    }

    @Test
    public void userDefineQuery() {
        testDataInitialize();

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        List<Comment> all1 = commentRepository.findAll();
        assertThat(all1.size()).isEqualTo(1);

        Comment commentDbParam = new Comment();
        String contentsOfComment = "meet";

        Page<Comment> dbInfo = null;

        GIVE:
        {
            commentDbParam.setContent(contentsOfComment);
        }

        WHEN:
        {
            dbInfo = commentRepository.findByContentContainsAndPost(contentsOfComment, all.get(0), PageRequest.of(0, 10));
        }

        THEN:
        {
            assertThat(dbInfo).isNotNull();
            assertThat(dbInfo.getTotalElements()).isEqualTo(1);
            dbInfo.getContent().forEach(System.out::println);
        }
    }
}