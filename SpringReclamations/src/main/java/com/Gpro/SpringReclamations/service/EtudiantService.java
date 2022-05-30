package com.Gpro.SpringReclamations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.repository.AdministrativeRepository;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;


@Service
public class EtudiantService {
	
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private AdministrativeService administrativeService;
    @Autowired
    private AdministrativeRepository administrativeRepository;
    
	public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> addAllEtudiants(List<Etudiant> etudiants) {
        return  etudiantRepository.saveAll(etudiants);
    }

    public Etudiant getEtudiantByID(int id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant getEtudiantByName(String nom) {
        return  etudiantRepository.findByNom(nom);
    }

    public Etudiant updateEtudiant(Etudiant etudiant) {
    	Etudiant existingGP = etudiantRepository.findById(etudiant.getId()).orElse(null);

        System.out.println(etudiant);
        if(existingGP == null) {
            System.out.println("etudiant not found");
            return  etudiantRepository.save(etudiant);
        }else  {
        	   existingGP.setCin(etudiant.getCin());
               existingGP.setNom(etudiant.getNom());
               existingGP.setPrenom(etudiant.getPrenom());
               existingGP.setMatricule(etudiant.getMatricule());
               existingGP.setUsername(etudiant.getUsername());
               existingGP.setEmail(etudiant.getEmail());
               existingGP.setPassword(etudiant.getPassword());
               existingGP.setGroupe(etudiant.getGroupe());
            etudiantRepository.save(existingGP);

        }

        return etudiant;
    }

    public boolean deleteEtudiantByID(int id) {
    	Etudiant existingGP = etudiantRepository.getById(id);
        if(existingGP != null) {
        	etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Etudiant> getAllEtudiant() {
        return etudiantRepository.findAll();
    }

}