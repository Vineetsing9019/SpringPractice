// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-27
package com.itvedant.basics.manytomany;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String gender;
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "allPatients")
    private List<Doctor> myDoctors;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Doctor> getMyDoctors() {
        return myDoctors;
    }

    public void setMyDoctors(List<Doctor> myDoctors) {
        this.myDoctors = myDoctors;
    }
}
