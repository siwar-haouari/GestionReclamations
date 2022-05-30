package com.Gpro.SpringReclamations.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer>{
	

	Optional<Enseignant> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	
	
	Enseignant getByNom(String nom);

	Enseignant findByNom(String nom);

	Enseignant getById(int id);

}
