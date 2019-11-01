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
        session.save(study);
    }


}
