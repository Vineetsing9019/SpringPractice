// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-25
package com.itvedant.shopkart.models;

import org.springframework.data.rest.core.config.Projection;

@Projection(types= {Cart.class})
public interface CartProjection {
	public int getId() ;
	public int getQuantity() ;
	public ProductProjection getProduct() ;
	
}

