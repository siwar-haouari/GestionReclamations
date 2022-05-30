package com.Gpro.SpringReclamations.controlleur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.ERole;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Role;
import com.Gpro.SpringReclamations.playload.request.SignupRequest;
import com.Gpro.SpringReclamations.playload.response.MessageResponse;
import com.Gpro.SpringReclamations.repository.AdministrativeRepository;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;
import com.Gpro.SpringReclamations.service.AdministrativeService;
import com.Gpro.SpringReclamations.service.EtudiantService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Administrative")
public class AdministrativeControlleur {

    @Autowired
    private AdministrativeService administrativeService;
    @Autowired
    private AdministrativeRepository administrativeRepository;
    @Autowired
    private EtudiantService EtudiantRepository;
    
    @Autowired
    private EtudiantRepository etudiantService;
    
    
    private Etudiant etudiant;

    // Add new groupe
    @PostMapping("/addAdministrative")
  	public ResponseEntity<?> addAdministrative(@Valid @RequestBody SignupRequest signUpRequest) {

		// Create new user's account
		Administrative user = new Administrative(signUpRequest.getSujet(),
				signUpRequest.getDescription(),
				signUpRequest.getEtat(),
				signUpRequest.getEtudiant()
							);


		administrativeRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("addAdministrative registered successfully!"));
				  
    }
    // Add more than 1 groupe
    @PostMapping("/addAdministratives")
    public List<Administrative> addAllAdministratives(@RequestBody List<Administrative> administratives) {
        return administrativeService.addAllAdministratives(administratives);
    }

    // Get employee by Id
    @GetMapping("/getAdministrativeByID/{id}")
    public List<Administrative> getAdministrativeByID(@PathVariable Integer id) {
        return administrativeService.getAdministrativeByID(id);
    }
    
    // afficher la liste des reclamations de chaque etudiant
    @GetMapping("/getAdministrativeByEtudiantId/{etudiant}")
    public List<Administrative> getAdministrativeByEtudiantId(@PathVariable  Etudiant etudiant) {
        return administrativeService.getAdministrativeByEtudiantId(etudiant);
    }

    // Get groupe by name
    @GetMapping("/getAdministrativeBySujet/{name}")
    public  Administrative getAdministrativeByName(@PathVariable String name) {
        return  administrativeService.getAdministrativeBySujet(name);
    }

    // Update groupe
    @PutMapping("/updateAdministrative")
    public Administrative updateAdministrative(@RequestBody Administrative administrative) {
        return administrativeService.updateAdministrative(administrative);
    }

    // Delete groupe
    @DeleteMapping("/deleteAdministrativeById/{id}")
    public boolean deleteAdministrativeByID(@PathVariable int id) {
        return administrativeService.deleteAdministrativeByID(id);
    }

    // Get all groupes
    @GetMapping("/getAll")
    public List<Administrative> getAllAdministrative() {
        return administrativeService.getAllAdministrative();
    }
    //traiter methode
    @PutMapping("/traiterAdministrative")
    public Administrative traiterAdministrative(@RequestBody Administrative administrative) {
        return administrativeService.traiterAdministrative(administrative);
    }

   
}