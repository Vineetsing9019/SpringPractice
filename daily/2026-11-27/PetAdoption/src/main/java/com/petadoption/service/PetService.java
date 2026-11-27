// Learning the service layer in a Spring Boot REST project - practice class from PetAdoption, pushed 2026-10-20
package com.petadoption.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petadoption.model.Pet;
import com.petadoption.repository.PetRepository;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public Pet updatePet(Long id, Pet updatedPet) {
        updatedPet.setId(id);
        return petRepository.save(updatedPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
