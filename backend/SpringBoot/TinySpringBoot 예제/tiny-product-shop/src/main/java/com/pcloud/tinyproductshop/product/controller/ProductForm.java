package com.pcloud.tinyproductshop.product.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
