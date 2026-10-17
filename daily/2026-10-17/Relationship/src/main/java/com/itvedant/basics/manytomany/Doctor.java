// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-17
package com.itvedant.basics.manytomany;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Doctor {
	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String specialization;
	
	@ManyToMany
	@JoinTable(
			name="doctors_patients",
			joinColumns =@JoinColumn(referencedColumnName = "id"),//Doctor
			inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))//patient
	               private List<Patient> allPatients;

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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Patient> getAllPatients() {
		return allPatients;
	}

	public void setAllPatients(List<Patient> allPatients) {
		this.allPatients = allPatients;
	}         
	
	
	

}

