// Learning repositories in a Spring Boot CRUD REST API - practice class from ProductCrudd, pushed 2026-10-31
package com.itvedant.productcrudd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itvedant.productcrudd.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}

