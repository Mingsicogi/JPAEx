package com.example.demo.event;

import com.example.demo.entity.Post;
import org.springframework.context.ApplicationEvent;

public class PostPublishEvent extends ApplicationEvent {

    private Post post;

    public PostPublishEvent(Object source) {
        super(source);
        this.post = (Post)source;
    }

    public Post getPost() {
        return post;
    }
}
