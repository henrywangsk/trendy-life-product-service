package com.henry.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.product.data.entity.ProductEntity;
import com.henry.product.data.repository.ProductRepository;
import com.henry.product.dto.Product;
import com.henry.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper modelMapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Product createProduct(Product prod) {
        final ProductEntity prodEntity = modelMapper.map(prod);
        final ProductEntity created = productRepository.save(prodEntity);

        return modelMapper.map(created);
    }

}
