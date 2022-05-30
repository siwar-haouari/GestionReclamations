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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name ="enseignant",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
	})

public class Enseignant extends Utilisateur{
	
	private String specialite;
	private String typeContrat;
	
	
	public Enseignant(int cin, String nom, String prenom, String typeContrat, String specialite,
			String username, String email, String password) {
		super(cin, nom, prenom, username, email, password);
		this.specialite = specialite;
		this.typeContrat = typeContrat;
	}

	
	public Enseignant(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}




	@OneToMany(mappedBy ="enseignant")
    @JsonBackReference  
	private List <Pedagogique> pedagogiques;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(	name = "enseignant_roles", 
				joinColumns = @JoinColumn(name = "enseignant_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
}
