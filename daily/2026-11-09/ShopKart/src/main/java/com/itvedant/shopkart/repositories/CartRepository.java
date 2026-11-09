// Learning repositories in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-23
package com.itvedant.shopkart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.shopkart.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}

