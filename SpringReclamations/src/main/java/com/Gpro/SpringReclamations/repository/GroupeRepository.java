package com.Gpro.SpringReclamations.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Groupe;
import com.Gpro.SpringReclamations.model.Role;


@Repository
public interface GroupeRepository extends JpaRepository<Groupe,Integer> {

	Groupe findByNom(String nom);

	//recherche par nom de groupe
	@Query("select g from Groupe g  where g.nom like %:x%")
	List<Groupe> rechercherParMc(@Param("x") String mc);

	
	


}
