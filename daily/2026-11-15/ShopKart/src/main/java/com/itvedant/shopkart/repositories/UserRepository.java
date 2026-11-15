// Learning repositories in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-29
package com.itvedant.shopkart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.itvedant.shopkart.models.User;
import com.itvedant.shopkart.models.UserProjection;

@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
