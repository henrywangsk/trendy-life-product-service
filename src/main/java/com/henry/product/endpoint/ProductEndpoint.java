package com.henry.product.endpoint;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.product.dto.Product;
import com.henry.product.service.ProductService;

@Service
@Path("/products")
public class ProductEndpoint {

    @Autowired
    private ProductService productService;

    @GET
    @Produces("application/json")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @POST
    @Consumes("application/json")
    public Response addProduct(Product prod, @Context UriInfo uriInfo) {
        final Long prodId = productService.addProduct(prod);
        final URI uri = uriInfo.getAbsolutePathBuilder().path(prodId.toString()).build();
        return Response.created(uri).build();
    }
}
