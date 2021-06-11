package com.pcloud.tinyproductshop.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto extends ProductDto {
    private String author;
    private String isbn;
}
