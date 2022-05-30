package com.Gpro.SpringReclamations.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.Etudiant;



@Repository
public interface AdministrativeRepository extends JpaRepository<Administrative,Integer>{


	
	
	
	Administrative getBySujet(String sujet);

	Administrative findBySujet(String sujet);

	Administrative getById(int id);
	
	@Query("select a from Administrative a  where a.etudiant like :x")
	List<Administrative> getAdministrativeByEtudiantId(@Param("x") Etudiant etudiant);
	
	List<Administrative> findByEtudiant(Etudiant etudiant);
	
	@Query("select a from Administrative a  where a.id like :x")
	List<Administrative> getAdministrativeById(@Param("x") Integer id);


}
