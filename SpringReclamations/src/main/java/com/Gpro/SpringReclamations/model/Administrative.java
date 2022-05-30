package com.Gpro.SpringReclamations.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="administrative")
public class Administrative extends Reclamation {


	/*@ManyToOne
	@JoinColumn(name="idAdministrateur")
	private Administrateur administrateur;*/
	


	@ManyToOne
	@JoinColumn(name="etudiant")
	private Etudiant etudiant;

    
	public Administrative(String sujet, String description, String etat,
			Etudiant etudiant) {
		super(sujet, description, etat);
		this.etudiant = etudiant;
	}
	

   
	

}
