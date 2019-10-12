package com.henry.product.endpoint;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.ProductApplication;
import com.henry.product.data.entity.ProductEntity;
import com.henry.product.data.repository.ProductRepository;
import com.henry.product.dto.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ProductEndpointIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getFullUrl(String uri) {
        return String.format("http://localhost:%s/api%s", port, uri);
    }
    
    private static final String sku = "3";
    private static final String title = "title 3";
    private static final String description = "description 3";
    private static final BigDecimal price = BigDecimal.valueOf(3.34);

    @Before
    public void setUp() throws Exception {
        repository.save(new ProductEntity("1", "title 1", "description 1", BigDecimal.valueOf(2.34)));
        repository.save(new ProductEntity(sku, title, description, price));
    }

    @Test
    public void requestProducts_returnAllProducts() {
        final ResponseEntity<List<Product>> response = restTemplate.exchange(getFullUrl("/products"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        final List<Product> products = response.getBody();
        assertEquals(2, products.size());
        
        final Product actualProduct = products.get(1);
        final Product expectedProduct = Product.of(actualProduct.getId(), sku, title, description, price, 0, false);
        assertEquals(expectedProduct, actualProduct);
    }

}
