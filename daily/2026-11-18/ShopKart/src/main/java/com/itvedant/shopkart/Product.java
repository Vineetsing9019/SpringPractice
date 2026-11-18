// Learning a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-01
package com.itvedant.shopkart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String name;
 private String category;
 private int price;
 private String imagePath;
 public int getId() {
	return id;
 }
 public void setId(int id) {
	this.id = id;
 }
 public String getName() {
	return name;
 }
 public void setName(String name) {
	this.name = name;
 }
 public String getCategory() {
	return category;
 }
 public void setCategory(String category) {
	this.category = category;
 }
 public int getPrice() {
	return price;
 }
 public void setPrice(int price) {
	this.price = price;
 }
 public String getImagePath() {
	return imagePath;
 }
 public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
 }
 
}
