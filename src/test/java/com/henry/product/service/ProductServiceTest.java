package com.henry.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.data.entity.ProductEntity;
import com.henry.product.data.repository.ProductRepository;
import com.henry.product.dto.Product;
import com.henry.product.mapper.ProductMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ModelMapper.class, ProductMapper.class, ProductServiceImpl.class })
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Test
    public void getAllProducts_returnAllProducts() {
        // preparation
        final ProductEntity pe0 = new ProductEntity("0", "title 0", "desc 0", BigDecimal.valueOf(3.20));
        pe0.setId(Long.valueOf(0));
        final ProductEntity pe1 = new ProductEntity("1", "title 1", "desc 1", BigDecimal.valueOf(3.30));
        pe1.setId(Long.valueOf(1));
        pe1.setInstallments(10);
        pe1.setFreeShipping(true);
        final List<ProductEntity> productEntities = Stream
                .of(pe0, pe1)
                .collect(Collectors.toList());
        when(repository.findAll()).thenReturn(productEntities);

        // operation
        final List<Product> actualProducts = productService.getAllProducts();

        // assertion
        final Product p0 = Product.of(Long.valueOf(0), "0", "title 0", "desc 0", BigDecimal.valueOf(3.20), 0, false);
        final Product p1 = Product.of(Long.valueOf(1), "1", "title 1", "desc 1", BigDecimal.valueOf(3.30), 10, true);
        final List<Product> expectedProducts = Stream
                .of(p0, p1)
                .collect(Collectors.toList());
        assertEquals(expectedProducts, actualProducts);
    }
}
