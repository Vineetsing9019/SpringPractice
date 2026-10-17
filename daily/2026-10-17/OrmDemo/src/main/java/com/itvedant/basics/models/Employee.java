// Learning entity models in ORM with Hibernate and JPA - practice class from OrmDemo, pushed 2026-10-17
package com.itvedant.basics.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Entity is  used to create table
//@Table(name
public class Employee {
	@Column(unique = true)
private String email;	

private int id;
@Column(nullable= false)
private String name;
private String department;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int salary;

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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}

}

