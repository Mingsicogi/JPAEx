package com.example.demo.listener;

import com.example.demo.event.PostPublishEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

public class PostListener /*implements ApplicationListener<PostPublishEvent>*/ {
//    @Override
    @EventListener
    public void onApplicationEvent(PostPublishEvent postPublishEvent) {
        System.out.println("==========================");
        System.out.println(postPublishEvent.getPost().getTitle() + " is published!!");
        System.out.println("==========================");
    }
}
