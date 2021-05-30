package com.pcloud.tinyproductshop.product.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="category_product", joinColumns = @JoinColumn(name="category_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
