package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity // db에 Account table과 매핑이 되는 것을 나타내는 annotation. hibernate 에서 사용하는 매핑명
@Table(name = "Account")// db에 생성되는 테이블 명 정의
public class Account {

    @Id // pk
    @GeneratedValue // auto increment
    private Long id;

    @Column(nullable = false, unique = true) // 모든 필드에 생략되어 있는 annotation
    @NotNull(message = "username cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    @Transient // 필드로만 사용. jpa에는 사용되지 않음.
    @JsonIgnore
    private String no;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "homeStreet"))
    })
    private Address address;

    public void setStudy(Account account, Study study) {
        study.setOwner(account);
        account.getStudies().add(study);
    }
}
