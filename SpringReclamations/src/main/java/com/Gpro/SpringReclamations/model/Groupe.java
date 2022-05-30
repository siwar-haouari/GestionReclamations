package com.Gpro.SpringReclamations.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id ;
	private String nom;
	
	@OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL,orphanRemoval = true)
	private List <Etudiant> Etudiants;
	


	
	
	public Groupe(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public Groupe(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}



}
