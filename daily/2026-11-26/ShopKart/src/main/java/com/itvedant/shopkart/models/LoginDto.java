// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart.models;

public class LoginDto {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

