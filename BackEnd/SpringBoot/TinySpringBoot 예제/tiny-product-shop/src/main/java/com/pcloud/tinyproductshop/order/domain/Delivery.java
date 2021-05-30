package com.pcloud.tinyproductshop.order.domain;

import com.pcloud.tinyproductshop.member.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;
    @OneToOne(mappedBy="delivery")
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
