package com.Gpro.SpringReclamations.service;

import java.util.List;

import com.Gpro.SpringReclamations.model.Groupe;

public interface IserviceGroupe {	
	
			public void ajouterGroupe(Groupe g);
			public List<Groupe> getAllGroupes();
			public Groupe getGroupeById(int Id);
			public List<Groupe> getGroupesBMC(String motcle);
			public void supprimerGroupe(int Id);
			public void mettreAJourGroupe(Groupe g);

}
