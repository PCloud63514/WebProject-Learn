package com.pcloud.tinyproductshop.product.controller;

import com.pcloud.tinyproductshop.product.domain.Category;
import com.pcloud.tinyproductshop.product.domain.Product;
import com.pcloud.tinyproductshop.product.domain.impl.Book;
import com.pcloud.tinyproductshop.product.dto.BookDto;
import com.pcloud.tinyproductshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("product")
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/new")
    public String createProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product/createProductForm";
    }

    @PostMapping("/new")
    public String createProduct(ProductForm productForm, BindingResult br) {
        ArrayList<Category> categories = new ArrayList<Category>();
        Category category = new Category();
        category.setName("Book");
        categories.add(category);

        Book book = new Book();

        book.setName(productForm.getName());
        book.setPrice(productForm.getPrice());
        book.setStockQuantity(productForm.getStockQuantity());
        book.setCategories(categories);
        book.setAuthor(productForm.getAuthor());
        book.setIsbn(productForm.getIsbn());

        productService.saveProduct(book);

        return "redirect:/";
    }

    @GetMapping
    public String productList(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "product/productList";
    }

    @GetMapping("/{id}/edit")
    public String updateProductForm(@PathVariable Long id, Model model) {
        Book book = (Book) productService.findOne(id).get();

        ProductForm productForm = new ProductForm();
        productForm.setId(book.getId());
        productForm.setName(book.getName());
        productForm.setPrice(book.getPrice());
        productForm.setStockQuantity(book.getStockQuantity());
        productForm.setAuthor(book.getAuthor());
        productForm.setIsbn(book.getIsbn());

        model.addAttribute("form", productForm);
        return "product/updateProductForm";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("form") ProductForm form, BindingResult br) {
        BookDto bookDto = new BookDto();
        bookDto.setName(form.getName());
        bookDto.setPrice(form.getPrice());
        bookDto.setStockQuantity(form.getStockQuantity());
        bookDto.setAuthor(form.getAuthor());
        bookDto.setIsbn(form.getIsbn());
        productService.updateProduct(id, bookDto);

        return "redirect:/product";
    }
}
