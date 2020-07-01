package com.example.demo.config;

import com.example.demo.listener.PostListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public PostListener postListner(){
        return new PostListener();
    }
}
