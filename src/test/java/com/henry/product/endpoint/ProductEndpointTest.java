package com.henry.product.endpoint;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.product.dto.Product;
import com.henry.product.service.ProductService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ProductEndpoint.class })
public class ProductEndpointTest {
    @MockBean
    private ProductService productService;

    @MockBean
    private UriInfo uriInfo;

    @MockBean
    private UriBuilder uriBuilder;

    @Autowired
    private ProductEndpoint productEndpoint;

    @Test
    public void getAllProducts_returnAllProducts() {
        // preparation
        final Product p0 = Product.of(Long.valueOf(0), "0", "title 0", "desc 0", BigDecimal.valueOf(3.20), 0, false);
        final Product p1 = Product.of(Long.valueOf(1), "1", "title 1", "desc 1", BigDecimal.valueOf(3.30), 10, true);
        final List<Product> expectedProducts = Stream.of(p0, p1).collect(Collectors.toList());
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        // operation
        final List<Product> prodList = productEndpoint.getAllProducts();

        // assertion
        assertEquals(expectedProducts, prodList);
    }

    public void addProduct_returnCreated() throws IllegalArgumentException, UriBuilderException, URISyntaxException {
        // preparation
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(uriBuilder);
        when(uriBuilder.path(anyString())).thenReturn(uriBuilder);
        final URI uri = new URI("http://localhost:9000/api//products/4");
        when(uriBuilder.build()).thenReturn(uri);

        final Product prod = new Product();
        prod.setSku("4");
        prod.setTitle("title 4");
        prod.setDescription("description 4");
        prod.setPrice(BigDecimal.valueOf(3.30));
        prod.setInstallments(10);
        prod.setFreeShipping(true);

        final Product expected = new Product();
        expected.setId(Long.valueOf(4));
        expected.setSku("4");
        expected.setTitle("title 4");
        expected.setDescription("description 4");
        expected.setPrice(BigDecimal.valueOf(3.30));
        expected.setInstallments(10);
        expected.setFreeShipping(true);
        when(productService.createProduct(prod)).thenReturn(expected);

        // operation
        final Response response = productEndpoint.addProduct(prod, uriInfo);

        // assertion
        assertEquals(Status.CREATED, response.getStatus());
        assertEquals(uri, response.getLocation());
        assertEquals(expected, response.getEntity());
    }
}
