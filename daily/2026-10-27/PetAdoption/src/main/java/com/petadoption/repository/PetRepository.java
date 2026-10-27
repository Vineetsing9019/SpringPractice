// Learning repositories in a Spring Boot REST project - practice class from PetAdoption, pushed 2026-10-27
package com.petadoption.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petadoption.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
