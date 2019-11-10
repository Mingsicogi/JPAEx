package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@SpringBootApplication
/**
 * hibernate가 쿼리를 만드는 방법
 *
 * 1. 메소드 이름을 분석해서 만들기(CREATE)
 * 2. 미리 정의해 둔 쿼리를 찾아서 수행하기(USED-DECLARE-QUERY)
 * 3. 미리 정의한 쿼리를 찾아보고 없으면 만들기(CREATE-IF-NOT_FOUND) - default(권장)
 *
 *  - 쿼리를 찾는 방법
 *      JPA Repository 순서
 *          1. @Query
 *          2. Procedure
 *          3. @NamedQuery
 *
 *  - 쿼리 만드는 방법
 *      리턴타입 {접두어}{도메인(생략 가능)}{도입부}By{프로퍼티 표현식}(조건식)[(And|Or){프로퍼티 표현식}(조건식)]{정렬 조건} (매개변수)
 **/
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class JpaDemoApplication {


    @RestController
    @RequestMapping("/test")
    @Transactional
    public class TestController{

        @PersistenceContext
        private EntityManager entityManager;

        @PostMapping("/account/save")
        public ResponseEntity<Account> save(@Valid Account account, BindingResult result){

            if(result.hasErrors()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            entityManager.persist(account);

            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

}
