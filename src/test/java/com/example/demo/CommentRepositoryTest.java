package com.example.demo;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
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
import java.util.UUID;
import java.util.stream.Stream;

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

    @Test
    public void userDefineQuery2() {
        String contentOfComment = "Hello JPA.";
        Comment comment = new Comment();

        List<Comment> dbInfoList = null;

        GIVE:
        {
            comment.setContent(contentOfComment);
            commentRepository.save(comment);
        }

        WHEN:
        {
            dbInfoList = commentRepository.findByContentContains("jpa");
        }

        THEN:
        {
            assertThat(dbInfoList.size()).isEqualTo(0);
        }
    }

    @Test
    public void userDefineQuery3() {
        String contentOfComment = "Hello JPA.";
        Comment comment = new Comment();

        List<Comment> dbInfoList = null;

        GIVE:
        {
            comment.setContent(contentOfComment);
            commentRepository.save(comment);
        }

        WHEN:
        {
            dbInfoList = commentRepository.findByContentContainsIgnoreCase("jpa");
        }

        THEN:
        {
            assertThat(dbInfoList.size()).isEqualTo(1);
        }
    }

    @Test
    public void userDefineQuery4() {
        String contentOfComment = "Hello JPA.";
        Comment comment = new Comment();

        List<Comment> dbInfoList = null;

        GIVE:
        {
            createComment(contentOfComment, 4);
        }

        WHEN:
        {
            dbInfoList = commentRepository.findByContentContainsIgnoreCaseAndLikeCountGreaterThan("jpa", 5);
        }

        THEN:
        {
            assertThat(dbInfoList.size()).isEqualTo(0);
        }
    }

    @Test
    public void userDefineQuery5() {

        List<Comment> dbInfoList = null;

        GIVE:
        {
            Stream.iterate(1, i -> i + 1).limit(30).forEach(n -> this.createComment("JPA" + UUID.randomUUID().toString(), n));
        }

        WHEN:
        {
//            dbInfoList = commentRepository.findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc("jpa", 15);
        }

        THEN:
        {
            assertThat(dbInfoList.size()).isGreaterThan(14);
            dbInfoList.forEach(System.out::println);
        }
    }

    @Test
    public void userDefineQuery6() {

        List<Comment> dbInfoList = null;

        GIVE:
        {
            Stream.iterate(1, i -> i + 1).limit(30).forEach(n -> this.createComment("JPA" + UUID.randomUUID().toString(), n));
        }

        WHEN:
        {
            dbInfoList = commentRepository.findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountAsc("jpa", 15);
        }

        THEN:
        {
            assertThat(dbInfoList.size()).isGreaterThan(14);
            dbInfoList.forEach(System.out::println);
        }
    }

    @Test
    public void userDefineQuery7() {

        Page<Comment> dbInfoList = null;

        GIVE:
        {
            Stream.iterate(1, i -> i + 1).limit(30).forEach(n -> this.createComment("JPA" + UUID.randomUUID().toString(), n));
        }

        WHEN:
        {
            PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

            dbInfoList = commentRepository.findByContentContainsIgnoreCaseAndLikeCountGreaterThan("jpa", 15, pageRequest);
        }

        THEN:
        {
            assertThat(dbInfoList.getNumberOfElements()).isEqualTo(10);
            assertThat(dbInfoList.getTotalPages()).isEqualTo(2);
            dbInfoList.forEach(System.out::println);
        }
    }

    @Test
    public void userDefineQuery8() {

        GIVE:
        {
            Stream.iterate(1, i -> i + 1).limit(30).forEach(n -> this.createComment("JPA" + UUID.randomUUID().toString(), n));
        }

        try (Stream<Comment> dbInfoList = commentRepository.findByContentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc("jpa", 15)) {
//            assertThat(dbInfoList.count()).isGreaterThan(14);
//            assertThat(dbInfoList.findFirst().get().getLikeCount()).isEqualTo(30);
            dbInfoList.forEach(System.out::println);
        }

    }

    private void createComment(String contentOfComment, int likeCount) {
        Comment comment = new Comment();
        comment.setContent(contentOfComment);
        comment.setLikeCount(likeCount);

        commentRepository.save(comment);
    }
}