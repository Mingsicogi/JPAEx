package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Study;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    @Test
    public void crud(){
        Study study = new Study();
        study.setName("KK");

        Account account = new Account();
        account.setUsername("minssogi");
        account.setPassword("123");

        study.setOwner(account);

        studyRepository.save(study);
        studyRepository.flush();

//        Predicate predicate = QStudy.study.name.containsIgnoreCase("k");
//        Optional<Study> repositoryOne = studyRepository.findOne(predicate);

//        assertThat(repositoryOne).isNotNull();
    }
}