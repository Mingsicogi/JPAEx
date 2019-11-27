package com.example.demo.config;

import com.example.demo.entity.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

//@Configuration
public class AuditAwareConfig implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("=========== Looking for account info ============");

        return Optional.empty();
    }
}
