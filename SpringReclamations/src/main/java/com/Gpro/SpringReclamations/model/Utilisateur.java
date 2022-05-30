package com.Gpro.SpringReclamations.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Table(name ="utilisateur")
public class Utilisateur implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id ;
	
	private int cin;
	private String nom;
	private String prenom;
	private String username;
	private String email;
	private String password;
	
	public Utilisateur(int cin, String nom, String prenom, String username, String email, String password) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Utilisateur(Integer id) {
		super();
		this.id = id;
	}
	
	
	
}
