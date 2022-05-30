package com.Gpro.SpringReclamations.model;


import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name ="etudiant",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
	})
public class Etudiant extends Utilisateur{
	
	private String matricule;
	
	
	public Etudiant(int cin, String nom, String prenom,String matricule, String username, String email, Groupe groupe,String password) {
		super(cin, nom, prenom, username, email, password);
         this.matricule = matricule;
 		 this.groupe = groupe;

}
	
	public Etudiant(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	


	
	@ManyToOne
	@JoinColumn(name="groupe")
	private Groupe groupe;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(	name = "etudiant_roles", 
				joinColumns = @JoinColumn(name = "etudiant_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	//administrative

	/*
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            },
            mappedBy = "etudiant")
    private Set<Administrative> administrative = new HashSet<>();*/
	
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference (value="administrative")
	private List <Administrative> administratives;


	
    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference (value="pedagogique") 
	private List <Pedagogique> pedagogiques;
	


}
