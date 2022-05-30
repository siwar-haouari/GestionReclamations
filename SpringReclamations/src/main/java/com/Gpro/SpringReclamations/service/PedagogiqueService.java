package com.Gpro.SpringReclamations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Pedagogique;
import com.Gpro.SpringReclamations.repository.PedagogiqueRepository;

@Service
public class PedagogiqueService{
	
	    @Autowired
	    private PedagogiqueRepository pedagogiqueRepository;
	
		public Pedagogique addPedagogique(Pedagogique pedagogique) {
	        return pedagogiqueRepository.save(pedagogique);
	    }

		//afficher la liste des reclamations Pedagogique de chaque etudiant
	    
	    public List<Pedagogique> getPedagogiqueByEtudiantId(Etudiant etudiant) {

			return pedagogiqueRepository.findByEtudiant(etudiant);
			}
	    
        //afficher la liste des reclamations Pedagogique de chaque enseignant
	    
	    public List<Pedagogique> getPedagogiqueByEnseignantId(Enseignant enseignant) {
	    	
			return pedagogiqueRepository.getPedagogiqueByEnseignantId(enseignant);
			}

	    public List<Pedagogique> addAllPedagogiques(List<Pedagogique> pedagogiques) {
	        return  pedagogiqueRepository.saveAll(pedagogiques);
	    }

	    public Pedagogique getPedagogiqueByID(int id) {
	        return pedagogiqueRepository.findById(id).orElse(null);
	    }

	    public Pedagogique updatePedagogique(Pedagogique pedagogique) {
	    	Pedagogique existingGP = pedagogiqueRepository.findById(pedagogique.getId()).orElse(null);
	        System.out.println(pedagogique);
	        if(existingGP == null) {
	            System.out.println("pedagogique not found");
	            return  pedagogiqueRepository.save(pedagogique);
	        }else  {
	            existingGP.setSujet(pedagogique.getSujet());
	            existingGP.setEtat(pedagogique.getEtat());
	            existingGP.setDescription(pedagogique.getDescription());
	            existingGP.setEnseignant(pedagogique.getEnseignant());
      
	            pedagogiqueRepository.save(existingGP);
	        }
	        return pedagogique;
	    }

	    public boolean deletePedagogiqueByID(int id) {
	    	Pedagogique existingGP = pedagogiqueRepository.getById(id);
	        if(existingGP != null) {
	        	pedagogiqueRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }

	    public List<Pedagogique> getAllPedagogiques() {
	        return pedagogiqueRepository.findAll();
	    }

	    //methode traiter
	    public Pedagogique traiterPedagogique(Pedagogique pedagogique) {
	    	pedagogique.setEtat("traité");
	        return pedagogiqueRepository.save(pedagogique);
	    }

}
