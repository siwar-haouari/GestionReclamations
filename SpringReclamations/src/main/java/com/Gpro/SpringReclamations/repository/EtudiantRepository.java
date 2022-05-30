package com.Gpro.SpringReclamations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Etudiant;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
	
	
	Optional<Etudiant> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	
	Etudiant getByNom(String nom);

	Etudiant findByNom(String nom);

	Etudiant getById(int id);
	


}
