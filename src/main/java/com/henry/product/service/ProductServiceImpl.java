package com.henry.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.product.dto.Product;
import com.henry.product.jpa.entity.ProductEntity;
import com.henry.product.jpa.repository.ProductRepository;
import com.henry.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper modelMapper;

	@Override
	public List<Product> getAllProducts() {
		final List<Product> result = new ArrayList<>();
		final List<ProductEntity> prodList = productRepository.findAll();
		
		for (final ProductEntity pe : prodList) {
			result.add(modelMapper.map(pe));
		}
        
        return result;
	}

	@Override
	public Long addProduct(Product prod) {
		final ProductEntity prodEntity = modelMapper.map(prod);
		final ProductEntity created = productRepository.save(prodEntity);
		return created.getId();
	}

}
