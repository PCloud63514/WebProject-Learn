package com.pcloud.tinyproductshop.product.service;

import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired ProductService productService;
    @Autowired IProductRepository productRepository;

    @Test
    void 상품추가() {
        Product product = new Product();
        product.setName("고성능 .Net Code 프로그래밍");
        product.setPrice(36000);
        product.addStock(200);
        productService.saveProduct(product);
    }

    @Test
    void 복수_상품_검색() {
        productService.findProducts();
    }

    @Test
    void 단일_상품_검색() {
        Product product = new Product();
        product.setName("고성능 .Net Code 프로그래밍");
        product.setPrice(36000);
        product.addStock(200);
        productService.saveProduct(product);

        Optional<Product> one = productService.findOne(product.getId());
        Assertions.assertEquals(product, one.get());
    }
}