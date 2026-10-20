// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-20
package com.itvedant.basics.one_many;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	
	@Id
	@GeneratedValue
	private int id;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getAllEmployee() {
		return allEmployee;
	}

	public void setAllEmployee(List<Employee> allEmployee) {
		this.allEmployee = allEmployee;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> allEmployee;
	
	public int getId() {
		return id;
		
		
	}

}
