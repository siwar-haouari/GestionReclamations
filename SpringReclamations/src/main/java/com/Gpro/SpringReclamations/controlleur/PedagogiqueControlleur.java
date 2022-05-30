package com.Gpro.SpringReclamations.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Pedagogique;
import com.Gpro.SpringReclamations.service.PedagogiqueService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Pedagogique")
public class PedagogiqueControlleur {
		
	    @Autowired
	    private PedagogiqueService pedagogiqueService;


	    // Add new groupe
	    @PostMapping("/addPedagogique" )
	    public Pedagogique addPedagogique(@RequestBody Pedagogique pedagogique) {
	        return pedagogiqueService.addPedagogique(pedagogique);
	    }
	    
	    // Add more than 1 groupe
	    @PostMapping("/addPedagogiques")
	    public List<Pedagogique> addAllPedagogiques(@RequestBody List<Pedagogique> pedagogiques) {
	        return pedagogiqueService.getAllPedagogiques();
	    }

	    // Get employee by Id
	    @GetMapping("/getPedagogiqueByID/{id}")
	    public Pedagogique getPedagogiqueByID(@PathVariable int id) {
	        return pedagogiqueService.getPedagogiqueByID(id);
	    }

	    // Update groupe
	    @PutMapping("/updatePedagogique")
	    public Pedagogique updatePedagogique(@RequestBody Pedagogique pedagogique) {
	        return pedagogiqueService.updatePedagogique(pedagogique);
	    }

	    // Delete groupe
	    @DeleteMapping("/deletePedagogiqueById/{id}")
	    public boolean deletePedagogiqueByID(@PathVariable int id) {
	        return pedagogiqueService.deletePedagogiqueByID(id);
	    }

	    // Get all groupes
	    @GetMapping("/getAll")
	    public List<Pedagogique> getAllPedagogique() {
	        return pedagogiqueService.getAllPedagogiques();
	    }
		    
	    // afficher la liste des reclamations pedagogique de chaque etudiant
	    @GetMapping("/getPedagogiqueByEtudiantId/{etudiant}")
	    public List<Pedagogique> getPedagogiqueByEtudiantId(@PathVariable  Etudiant etudiant) {
	        return pedagogiqueService.getPedagogiqueByEtudiantId(etudiant);
	    }
	    
	    // afficher la liste des reclamations pedagogique de chaque enseignant
	    @GetMapping("/getPedagogiqueByEnseignantId/{enseignant}")
	    public List<Pedagogique> getPedagogiqueByEnseignantId(@PathVariable  Enseignant enseignant) {
	        return pedagogiqueService.getPedagogiqueByEnseignantId(enseignant);
	    }
	    
	    //traiter methode
	    @PutMapping("/traiterPedagogique")
	    public Pedagogique traiterPedagogique(@RequestBody Pedagogique pedagogique) {
	        return pedagogiqueService.traiterPedagogique(pedagogique);
	    }

	    



}
