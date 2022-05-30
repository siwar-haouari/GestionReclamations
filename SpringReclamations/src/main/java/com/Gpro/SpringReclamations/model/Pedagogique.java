package com.Gpro.SpringReclamations.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="pedagogique")
public class Pedagogique extends Reclamation {
	

	
	@ManyToOne
	
	@JoinColumn(name="enseignant")
	//@JsonProperty(access=Access.WRITE_ONLY)
	private Enseignant enseignant;
	
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name="etudiant")
	private Etudiant etudiant;



	


}
