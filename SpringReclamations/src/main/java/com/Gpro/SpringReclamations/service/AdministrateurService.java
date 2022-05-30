package com.Gpro.SpringReclamations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

	public Administrateur addAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public List<Administrateur> addAllAdministrateurs(List<Administrateur> administrateurs) {
        return  administrateurRepository.saveAll(administrateurs);
    }

    public Administrateur getAdministrateurByID(int id) {
        return administrateurRepository.getById(id);
    }

    public Administrateur getAdministrateurByName(String nom) {
        return  administrateurRepository.findByNom(nom);
    }

    public Administrateur updateAdministrateur(Administrateur administrateur) {
    	Administrateur existingGP = administrateurRepository.getById(administrateur.getId());
        System.out.println(administrateur);
        if(existingGP == null) {
            System.out.println("Administrateur not found");
            return  administrateurRepository.save(administrateur);
        }else  {
            existingGP.setNom(administrateur.getNom());
            administrateurRepository.save(existingGP);
        }
        return administrateur;
    }

    public boolean deleteAdministrateurByID(Long id) {
    	Administrateur existingGP = administrateurRepository.getById(id);
        if(existingGP != null) {
        	administrateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Administrateur> getAllAdministrateur() {
        return administrateurRepository.findAll();
    }

}
