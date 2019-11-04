package com.example.demo;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // entity manager object를 통해 영속화를 할 수 있다.(데이터 저장)

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("minseok");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("JPA Study");

        // 양방향 관계 설정.
        account.setStudy(account, study);

//        entityManager.persist(account); // jpa
        Session session = entityManager.unwrap(Session.class); // hibernate
        session.save(account);
        session.save(study); // (Write behind)

        // 데이터를 조회하면?
        // insert -> select 가될까? => no! 어차피 같은 데이터이기 때문에 캐쉬된 객체를 사용함.
        Account temp = session.load(Account.class, account.getId());
        System.out.println("########### " + temp.toString());

        account.setUsername("Ming"); // update 쿼리를 자동으로 실행함.
        account.setUsername("minseok");// 원래 데이터로 다시 변경된 것을 판단, update를 진행하지 않음(Dirty checking)


        Post post = new Post();
        post.setTitle("첫번째 게시글 등장");

        Comment comment1 = new Comment();
        comment1.setContent("댓글1");
        post.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("댓글2");
        post.addComment(comment2);

        session.save(post);

        // casecade remove test
//        Post post1 = session.get(Post.class, 3L);
//        session.delete(post1);
    }


}
