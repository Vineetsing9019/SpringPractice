// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-16
package com.itvedant.basics.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "doctors")
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
