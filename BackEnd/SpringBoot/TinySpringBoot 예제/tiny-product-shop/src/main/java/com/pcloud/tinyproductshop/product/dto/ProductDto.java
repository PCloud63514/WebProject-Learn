package com.pcloud.tinyproductshop.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {
    private String name;
    private int price;
    private int stockQuantity;
}
