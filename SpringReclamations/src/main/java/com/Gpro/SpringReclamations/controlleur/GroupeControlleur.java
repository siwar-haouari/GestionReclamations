package com.Gpro.SpringReclamations.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.Gpro.SpringReclamations.model.Groupe;
import com.Gpro.SpringReclamations.service.GroupeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/groupe")
@PreAuthorize("hasRole('ADMIN')")
public class GroupeControlleur {
	
	    @Autowired
	    private GroupeService groupeService;


	    // Add new groupe
	    @PostMapping("/addgroupe")
	    public Groupe addGroupe(@RequestBody Groupe groupe) {
	        return groupeService.addGroupe(groupe);
	    }
	    // Add more than 1 groupe
	    @PostMapping("/addGroupes")
	    public List<Groupe> addAllGroupes(@RequestBody List<Groupe> groupes) {
	        return groupeService.addAllGroupes(groupes);
	    }

	    // Get employee by Id
	    @GetMapping("/getGroupeByID/{id}")
	    public Groupe getGroupeByID(@PathVariable int id) {
	        return groupeService.getGroupeByID(id);
	    }

	    // Get groupe by name
	    @GetMapping("/getGroupeByName/{name}")
	    public  Groupe getGroupeByName(@PathVariable String name) {
	        return  groupeService.getGroupeByName(name);
	    }

	    // Update groupe
	    @PutMapping("/updateGroupe")
	    public Groupe updateGroupe(@RequestBody Groupe groupe) {
	        return groupeService.updateGroupe(groupe);
	    }

	    // Delete groupe
	    @DeleteMapping("/deleteGroupeById/{id}")
	    public boolean deleteGroupeByID(@PathVariable int id) {
	        return groupeService.deleteGroupeByID(id);
	    }

	    // Get all groupes
	    @GetMapping("/getAll")
	    public List<Groupe> getAllGroupe() {
	        return groupeService.getAllGroupe();
	    }

	   
}
