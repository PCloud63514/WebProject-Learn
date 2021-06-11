package com.pcloud.tinyproductshop.product.domain.impl;

import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Product {
    private String director;
    private String actor;
}
