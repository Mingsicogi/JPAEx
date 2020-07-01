package com.example.demo;

import com.example.demo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class QueryDSLTest {

    @Autowired
    private AccountRepository accountRepository;

//    @Test
//    public void crud(){
//        QAccount account = QAccount.account;
//        Predicate predicate = account
//                .username.containsIgnoreCase("minseok")
//                .and(account.password.contains("hibernate"));
//
//        Iterable<Account> accounts = accountRepository.findAll(predicate);
//
//        assertThat(accounts).isNotNull();
//    }
}
