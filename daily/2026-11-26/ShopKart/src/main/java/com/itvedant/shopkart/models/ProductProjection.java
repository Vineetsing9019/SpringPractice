// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart.models;

import org.springframework.data.rest.core.config.Projection;

@Projection(types= {Product.class})
public interface ProductProjection {
	public String getDescription() ;
	public int getId();
	public String getName() ;
	public String getCategory() ;
	public int getPrice() ;
	public String getImagePath() ;
}

