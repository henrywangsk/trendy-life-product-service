package com.henry.product.service;

import java.util.List;

import com.henry.product.dto.Product;

public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(Product prod);
}
