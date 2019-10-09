package com.henry.product.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.henry.product.dto.Product;
import com.henry.product.jpa.entity.ProductEntity;

import lombok.extern.java.Log;

@Component
@Log
public class ProductMapper {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public Product map(ProductEntity entity) {
		final Product product = modelMapper.map(entity, Product.class);
		log.fine(entity.toString());
		log.fine(product.toString());
		return product;
	}

	public ProductEntity map(Product product) {
		final ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
		log.fine(product.toString());
		log.fine(productEntity.toString());
		return productEntity;
	}
}
