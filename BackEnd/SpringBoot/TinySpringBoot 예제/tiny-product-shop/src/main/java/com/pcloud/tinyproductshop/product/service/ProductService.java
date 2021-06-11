package com.pcloud.tinyproductshop.product.service;

import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.domain.impl.Book;
import com.pcloud.tinyproductshop.product.dto.BookDto;
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

    @Transactional
    public void updateProduct(Long id, BookDto bookDto) {
        Book product = (Book)productRepository.findOne(id).get();
        product.setName(bookDto.getName());
        product.setPrice(bookDto.getPrice());
        product.setStockQuantity(bookDto.getStockQuantity());
        product.setAuthor(bookDto.getAuthor());
        product.setIsbn(bookDto.getIsbn());
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long id) {
        return productRepository.findOne(id);
    }
}
