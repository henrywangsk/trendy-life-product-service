package com.henry.product.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.henry.product.endpoint.ProductEndpoint;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ProductEndpoint.class);
	}
}
