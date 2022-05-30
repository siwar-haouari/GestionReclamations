package com.Gpro.SpringReclamations.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Pedagogique;

@Repository
public interface PedagogiqueRepository extends JpaRepository<Pedagogique,Integer>{

	//par etudiant
	@Query("select a from Pedagogique a  where a.etudiant like :x")
	List<Pedagogique> getPedagogiqueByEtudiantId(@Param("x") Etudiant etudiant);
	
	List<Pedagogique> findByEtudiant(Etudiant etudiant);
	
	
	//par enseignant 
	@Query("select a from Pedagogique  a where a.enseignant like :x AND a.etat like 'traité'")
	List<Pedagogique> getPedagogiqueByEnseignantId(@Param("x") Enseignant enseignant);
	
	List<Pedagogique> findByEnseignant(Enseignant enseignant);
}
