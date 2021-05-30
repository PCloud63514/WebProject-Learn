package com.pcloud.tinyproductshop.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Product {
    @Id @GeneratedValue
    @Column(name="product_id")
    private Long Id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();
}
