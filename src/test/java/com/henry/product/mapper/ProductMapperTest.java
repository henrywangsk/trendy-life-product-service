package com.henry.product.mapper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.data.entity.ProductEntity;
import com.henry.product.dto.Product;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ModelMapper.class, ProductMapper.class})
public class ProductMapperTest {
    private final static Long id = Long.valueOf(0);
    private final static String sku = "0";
    private final static String title = "title 0";
    private final static String description = "desc 0";
    private final static BigDecimal price = BigDecimal.valueOf(3.33);
    private final static int installments = 9;
    private final static boolean isFreeShipping = true;

    @Autowired
    private ProductMapper productMapper;
    
    @Test
    public void mapProduct_returnProductEntity() {
        // preparation
        final Product product = new Product();
        product.setSku(sku);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setInstallments(installments);
        product.setFreeShipping(isFreeShipping);
        
        // operation
        final ProductEntity prodEntity = productMapper.map(product);
        
        // assertion
        final ProductEntity expectedProdEntity = new ProductEntity(sku, title, description, price);
        expectedProdEntity.setInstallments(installments);
        expectedProdEntity.setFreeShipping(isFreeShipping);
        
        assertEquals(expectedProdEntity, prodEntity);
    }
    
    @Test
    public void mapProductEntity_returnProduct() {
        // preparation
        final ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setSku(sku);
        productEntity.setTitle(title);
        productEntity.setDescription(description);
        productEntity.setPrice(price);
        productEntity.setInstallments(installments);
        productEntity.setFreeShipping(isFreeShipping);
        
        // operation
        final Product product = productMapper.map(productEntity);
        
        // assertion
        final Product expectedProd = Product.of(id, sku, title, description, price, installments, isFreeShipping);
        assertEquals(expectedProd, product);
    }
}
