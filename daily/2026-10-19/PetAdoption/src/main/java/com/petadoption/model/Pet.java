// Learning entity models in a Spring Boot REST project - practice class from PetAdoption, pushed 2026-10-19
package com.petadoption.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // defines the table  structure,treat it as a blueprint for a database table.
public class Pet {
	
@Id // generates primary  key for us 
@GeneratedValue(strategy = GenerationType.IDENTITY) // is use for autoincremennt of id 

private Long id;
private String name;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBreed() {
	return breed;
}
public void setBreed(String breed) {
	this.breed = breed;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public boolean isAdopted() {
	return adopted;
}
public void setAdopted(boolean adopted) {
	this.adopted = adopted;
}
private String breed;
private int age;
private boolean adopted;
	

}

