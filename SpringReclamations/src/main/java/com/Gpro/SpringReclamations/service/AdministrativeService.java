package com.Gpro.SpringReclamations.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.repository.AdministrativeRepository;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;


@Service
public class AdministrativeService {
	
    @Autowired
    private AdministrativeRepository administrativeRepository;
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    //afficher la liste des reclamations administratives de chaque etudiant
    
    public List<Administrative> getAdministrativeByEtudiantId(Etudiant etudiant) {

		return administrativeRepository.findByEtudiant(etudiant);
		}


	public Administrative addAdministrative(Administrative administrative) {
    	administrative.setEtat("en cours");
        return administrativeRepository.save(administrative);
    }

    public List<Administrative> addAllAdministratives(List<Administrative> administratives) {
        return  administrativeRepository.saveAll(administratives);
    }

    public List<Administrative> getAdministrativeByID(@PathVariable Integer id) {
        return administrativeRepository.getAdministrativeById(id);
    }

    public Administrative getAdministrativeBySujet(String sujet) {
        return  administrativeRepository.findBySujet(sujet);
    }

    public Administrative updateAdministrative(Administrative administrative) {
    	Administrative existingGP = administrativeRepository.findById(administrative.getId()).orElse(null);
        System.out.println(administrative);
        if(existingGP == null) {
            System.out.println("Administrative not found");
            return  administrativeRepository.save(administrative);
        }else  {
            //existingGP.setEtat(administrative.getEtat());
            existingGP.setSujet(administrative.getSujet());
            existingGP.setDescription(administrative.getDescription());

            administrativeRepository.save(existingGP);
        }
        return administrative;
    }

    public boolean deleteAdministrativeByID(int id) {
    	Administrative existingGP = administrativeRepository.getById(id);
        if(existingGP != null) {
        	administrativeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Administrative> getAllAdministrative() {
        return administrativeRepository.findAll();
    }
    
    //methode traiter
    public Administrative traiterAdministrative(Administrative administrative) {
    	administrative.setEtat("traité");
        return administrativeRepository.save(administrative);
    }


}