package com.Gpro.SpringReclamations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.repository.EnseignantRepository;

@Service
public class EnseignantService {
	@Autowired
    private EnseignantRepository enseignantRepository;

	public Enseignant addEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> addAllEnseignants(List<Enseignant> enseignants) {
        return  enseignantRepository.saveAll(enseignants);
    }

    public Enseignant getEnseignantByID(int id) {
        return enseignantRepository.findById(id).orElse(null);
    }

    public Enseignant getEnseignantByName(String nom) {
        return  enseignantRepository.findByNom(nom);
    }

    public Enseignant updateEnseignant(Enseignant Enseignant) {
    	Enseignant existingGP = enseignantRepository.getById(Enseignant.getId());
        System.out.println(Enseignant);
        if(existingGP == null) {
            System.out.println("Enseignant not found");
            return  enseignantRepository.save(Enseignant);
        }else  {
            existingGP.setCin(Enseignant.getCin());
            existingGP.setNom(Enseignant.getNom());
            existingGP.setPrenom(Enseignant.getPrenom());
            existingGP.setSpecialite(Enseignant.getSpecialite());
            existingGP.setTypeContrat(Enseignant.getTypeContrat());
            existingGP.setUsername(Enseignant.getUsername());
            existingGP.setEmail(Enseignant.getEmail());
            existingGP.setPassword(Enseignant.getPassword());
            
            enseignantRepository.save(existingGP);
        }
        return Enseignant;
    }

    public boolean deleteEnseignantByID(int id) {
    	Enseignant existingGP = enseignantRepository.getById(id);
        if(existingGP != null) {
        	enseignantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Enseignant> getAllEnseignant() {
        return enseignantRepository.findAll();
    }

}