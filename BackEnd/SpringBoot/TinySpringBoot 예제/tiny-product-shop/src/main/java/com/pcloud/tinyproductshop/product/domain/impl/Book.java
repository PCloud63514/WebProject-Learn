package com.pcloud.tinyproductshop.product.domain.impl;

import com.pcloud.tinyproductshop.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Product {
    private String author;
    private String isbn;
}
