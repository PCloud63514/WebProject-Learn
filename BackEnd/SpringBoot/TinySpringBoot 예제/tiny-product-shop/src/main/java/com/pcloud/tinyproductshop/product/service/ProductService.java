package com.pcloud.tinyproductshop.product.service;

import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long id) {
        return productRepository.findOne(id);
    }
}
