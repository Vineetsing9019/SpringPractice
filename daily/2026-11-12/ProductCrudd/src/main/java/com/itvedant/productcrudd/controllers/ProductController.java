// Learning REST controllers in a Spring Boot CRUD REST API - practice class from ProductCrudd, pushed 2026-10-26

package com.itvedant.productcrudd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.productcrudd.models.Product;
import com.itvedant.productcrudd.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. adding new product to database

    // @PostMapping("/products")
    // public Product addProduct(@RequestBody Product product) {
    //     System.out.println(product);
    //     Product dbProduct = productService.addProduct(product);
    //     return dbProduct;
    // }

    // @GetMapping("/products")
    // public Iterable<Product> getAllProducts() {
    //     Iterable<Product> allProducts = productService.getAllProducts();
    //     return allProducts;
    // }

    // @GetMapping("/products/{id}")
    // public Product getProductById(@PathVariable("id") int id) {
    //     Product requiredProduct = productService.getProductById(id);
    //     return requiredProduct;
    // }

    // "[http://localhost:8080/products/2]" DELETE

    // @DeleteMapping("/products/{id}")
    // public String deleteProductById(@PathVariable int id) {
    //     productService.deleteProductById(id);
    //     return "product deleted successfully";
    // }

    // "[http://localhost:8080/products/1]" put, with json

    // @PutMapping("/products/{id}")
    // public Product updateProductById(@PathVariable int id, @RequestBody Product product) {
    //     Product updatedProduct = productService.updateProductById(product);
    //     return updatedProduct;
    // }


    // 1. adding new product to database

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {

        // System.out.println(product);

        Product dbProduct = productService.addProduct(product);

        return new ResponseEntity<>(dbProduct, HttpStatus.CREATED);
    }


    // 2. fetching all products

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {

        Iterable<Product> allProducts = productService.getAllProducts();

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {

        Product requiredProduct = productService.getProductById(id);

        return new ResponseEntity<>(requiredProduct, HttpStatus.OK);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable int id) {

        productService.deleteProductById(id);

        return new ResponseEntity<>("product deleted successfully", HttpStatus.NO_CONTENT);
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable int id, @RequestBody Product product) {

        Product updatedProduct = productService.updateProductById(product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

}


