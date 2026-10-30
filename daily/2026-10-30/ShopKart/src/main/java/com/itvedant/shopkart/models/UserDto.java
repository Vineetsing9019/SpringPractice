// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-30
package com.itvedant.shopkart.models;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
	@NotNull
	private String username;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min=6, max=12)
	@Pattern(regexp = "[a-zA-Z0-9@_#]*")
	private String password;
	@NotNull
	@Size(min=10, max=10)
	private String mobile;
	private List<String> roles;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	

}

