package com.Gpro.SpringReclamations.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.model.Groupe;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long>{
	
	Optional<Administrateur> findByUsername( String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	
	
	Administrateur getByNom(String nom);

	Administrateur findByNom(String nom);

	Administrateur getById(int id);


	

}
