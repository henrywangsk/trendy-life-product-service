package com.henry.product.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.data.entity.ProductEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll_returnAllProducts() {
        // prepare
        final ProductEntity pe1 = new ProductEntity("1", "title 1", "description 1", BigDecimal.valueOf(2.34));
        entityManager.persist(pe1);
        final ProductEntity pe2 = new ProductEntity("3", "title 3", "description 3", BigDecimal.valueOf(3.34));
        entityManager.persist(pe2);
        entityManager.flush();

        // operation
        final List<ProductEntity> prodList = new ArrayList<>();
        productRepository.findAll().forEach(pe -> prodList.add(pe));

        // validate
        assertEquals(2, prodList.size());
        
        final ProductEntity pe2Actual = prodList.get(1);
        pe2.setId(pe2Actual.getId()); // the only difference should be the ID, so set it
        assertEquals(pe2, pe2Actual);
    }
    
    public void saveProduct_returnCreated() {
        // prepare
        final ProductEntity pe = new ProductEntity("1", "title 1", "description 1", BigDecimal.valueOf(2.34));
        final int installments = 9;
        pe.setInstallments(installments);
        pe.setFreeShipping(true);
        
        // operation
        final ProductEntity created = productRepository.save(pe);
        
        // validation
        final ProductEntity actual = entityManager.find(ProductEntity.class, created.getId());
        assertNotNull(actual);
        assertEquals(installments, actual.getInstallments());
        assertTrue(actual.isFreeShipping());
    }
}
