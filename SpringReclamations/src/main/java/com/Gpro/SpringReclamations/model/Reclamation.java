package com.Gpro.SpringReclamations.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Table(name ="reclamation")
public class Reclamation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id ;
	
	private String sujet ;
	private String description ;
	private String etat ;
	
	public Reclamation(String sujet, String description, String etat) {
		super();
		this.sujet = sujet;
		this.description = description;
		this.etat = etat;
	}


	

	

	

	

	
	


}
