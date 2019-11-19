package com.example.demo.entity;

import com.example.demo.event.PostPublishEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/) // 주인 설정. fetch 기본 값은 lazy
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setPost(this);
    }

    public Post publish() {
        this.registerEvent(new PostPublishEvent(this));
        return this;
    }
}
