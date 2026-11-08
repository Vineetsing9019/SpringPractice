// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-23
package com.itvedant.shopkart.models;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {User.class})
public interface UserProjection {
public int getId();
public String getUsername();
public String getEmail();
public String getMobile();
public List<String> getRoles();
}

