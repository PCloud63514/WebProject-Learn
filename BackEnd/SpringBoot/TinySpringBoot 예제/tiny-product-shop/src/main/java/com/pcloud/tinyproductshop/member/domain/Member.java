package com.pcloud.tinyproductshop.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcloud.tinyproductshop.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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


    //==생성 로직==//
    public static Member create(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);

        return member;
    }
}
