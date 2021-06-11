package com.pcloud.tinyspringboot.member.domain;

import javax.persistence.*;

//ORG(Object Relational Mapping) 객체 관계 맵핑)
@Entity
public class Member {

    // Identity 전략: db에서 값을 자동으로 생성해주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name="username") db컬럼 명이 username일 경우 매핑해주는 용도
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
