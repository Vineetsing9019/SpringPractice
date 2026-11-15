// Learning the service layer in a Spring Boot CRUD REST API - practice class from ProductCrudd, pushed 2026-10-29
package com.itvedant.productcrudd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.productcrudd.models.Product;
import com.itvedant.productcrudd.repositories.ProductRepository;

@Service
public class ProductService {

@Autowired
private ProductRepository  productRepository;

public Product addProduct(Product product) {
	// add the product to db using repository
	Product dbProduct = productRepository.save(product);
	System.out.println("product object received from repository:"+ dbProduct);
	// send the  newly added product
	return dbProduct;
	
}




public Iterable<Product> getAllProducts() {
	Iterable<Product> allProducts = productRepository.findAll();
return allProducts;	
}

public Product getProductById(int id) {
	Product requiredProduct = productRepository.findById(id).get();
	return requiredProduct;
}

public void deleteProductById(int id) {
	productRepository.deleteById(id);
}

public Product updateProductById(Product product) {
	Product updatedProduct = productRepository.save(product);
	return updatedProduct;
	
	
	
}






}

