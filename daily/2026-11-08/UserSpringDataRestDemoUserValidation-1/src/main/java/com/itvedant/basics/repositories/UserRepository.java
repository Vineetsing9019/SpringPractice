// Learning repositories in Spring Data REST with validation - practice class from UserSpringDataRestDemoUserValidation-1, pushed 2026-10-22
package com.itvedant.basics.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.itvedant.basics.models.User;
import com.itvedant.basics.userprojection.UserProjection;



@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, Integer> {
	//save-insert
	//deleteById - delete
	//findAll - select
	//findById - select
	
	
	
	
	//http://localhost:8080/users/search/findByEmail?email=neha@gmail.com
	
	public Optional<User> findByEmail(String email);
	
	//http://localhost:8080/users/search/findByEmailAndPassword?email=pooja@gmail.com&password=pooja@123
	//public Optional<User> findByEmailAndPassword(String email, String password);
	
	
	
	//http://localhost:8080/users/search/login?email=pooja@gmail.com&password=pooja@123
	@RestResource(path = "/login")
	public Optional<User> findByEmailAndPassword(String email, String password);
	
	//http://localhost:8080/users/search/findByUserName?username=pooja
	public List<User> findByUsername(String username);
	

}
