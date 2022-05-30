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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="administrateur",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
	})
public class Administrateur extends Utilisateur {


	public Administrateur(int cin, String nom, String prenom, String username, String email, String password) {
		super(cin, nom, prenom, username, email, password);
	}

	/*@OneToMany(mappedBy ="administrateur")
	private List <Etudiant> etudiants;
	
	@OneToMany(mappedBy ="administrateur")
	private List <Pedagogique> Pedagogiques;
	
	@OneToMany(mappedBy ="administrateur")
	private List <Administrative> administratives;
	
	@OneToMany(mappedBy ="administrateur")
	private List <Enseignant> enseignants;
	
	@OneToMany(mappedBy ="administrateur")
	private List <Groupe> groupes;*/

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(	name = "admin_roles", 
				joinColumns = @JoinColumn(name = "admin_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	

	

	

}

