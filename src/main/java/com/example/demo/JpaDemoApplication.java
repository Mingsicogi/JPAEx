package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
