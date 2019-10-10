package com.henry.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.dto.Product;
import com.henry.product.jpa.entity.ProductEntity;
import com.henry.product.jpa.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Test
    public void getAllProducts() {
        final List<ProductEntity> productEntities = Stream
                .of(ProductEntity.of(Long.valueOf(0), "0", "title 0", "desc 0", BigDecimal.valueOf(3.20)),
                        ProductEntity.of(Long.valueOf(1), "1", "title 1", "desc 1", BigDecimal.valueOf(3.30)))
                .collect(Collectors.toList());
        when(repository.findAll()).thenReturn(productEntities);

        final List<Product> actualProducts = productService.getAllProducts();
        
        final List<Product> expectedProducts = Stream
                .of(Product.of(Long.valueOf(0), "0", "title 0", "desc 0", BigDecimal.valueOf(3.20), 0, false),
                        Product.of(Long.valueOf(1), "1", "title 1", "desc 1", BigDecimal.valueOf(3.30), 0, false))
                .collect(Collectors.toList());
//        assertEquals(2, actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
    }
}
