// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-24
package com.itvedant.shopkart.models;

import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
@Id
@GeneratedValue
public class Products {
	private int id;
	private String name ;
	private String category;
	private int price;
	private String imagePath;
	private String description;
	
	@OneToMany(mappedBy = "product")
	private List<Cart> allCart;
}

