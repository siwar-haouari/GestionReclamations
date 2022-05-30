package com.Gpro.SpringReclamations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gpro.SpringReclamations.model.Groupe;
import com.Gpro.SpringReclamations.repository.GroupeRepository;

@Service
public class GroupeService {
	
	    @Autowired
	    private GroupeRepository groupeRepository;

		public Groupe addGroupe(Groupe groupe) {
	        return groupeRepository.save(groupe);
	    }

	    public List<Groupe> addAllGroupes(List<Groupe> groupes) {
	        return  groupeRepository.saveAll(groupes);
	    }

	    public Groupe getGroupeByID(int id) {
	        return groupeRepository.findById(id).orElse(null);
	    }

	    public Groupe getGroupeByName(String nom) {
	        return  groupeRepository.findByNom(nom);
	    }

	    public Groupe updateGroupe(Groupe groupe) {
	    	Groupe existingGP = groupeRepository.findById(groupe.getId()).orElse(null);
	        System.out.println(groupe);
	        if(existingGP == null) {
	            System.out.println("groupe not found");
	            return  groupeRepository.save(groupe);
	        }else  {
	            existingGP.setNom(groupe.getNom());
	            groupeRepository.save(existingGP);
	        }
	        return groupe;
	    }

	    public boolean deleteGroupeByID(int id) {
	        Groupe existingGP = groupeRepository.getById(id);
	        if(existingGP != null) {
	        	groupeRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }

	    public List<Groupe> getAllGroupe() {
	        return groupeRepository.findAll();
	    }

}
