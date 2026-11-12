// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-26
package com.itvedant.basics.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository< Patient, Integer> {

}
