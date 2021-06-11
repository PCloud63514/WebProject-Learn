package com.pcloud.tinyproductshop.product.repository;

import com.pcloud.tinyproductshop.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    void save(Product product);
    Optional<Product> findOne(Long id);
    List<Product> findAll();
}
