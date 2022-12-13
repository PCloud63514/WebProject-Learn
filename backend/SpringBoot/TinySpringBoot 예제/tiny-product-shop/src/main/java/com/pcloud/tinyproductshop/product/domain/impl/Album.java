package com.pcloud.tinyproductshop.product.domain.impl;

import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Product {
    private String artist;
    private String etc;
}
