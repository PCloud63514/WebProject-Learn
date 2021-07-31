package com.pcloud.tinyproductshop.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcloud.tinyproductshop.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy="member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    //==생성 로직==//
    public static Member create(String name, Address address) {
        Member member = new Member(0L, name, address);

        return member;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
