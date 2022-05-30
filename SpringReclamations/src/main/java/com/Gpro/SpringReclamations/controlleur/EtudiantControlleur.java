package com.Gpro.SpringReclamations.controlleur;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.model.Administrative;
import com.Gpro.SpringReclamations.model.ERole;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Groupe;
import com.Gpro.SpringReclamations.model.Role;
import com.Gpro.SpringReclamations.model.Utilisateur;
import com.Gpro.SpringReclamations.playload.request.SignupRequest;
import com.Gpro.SpringReclamations.playload.response.MessageResponse;
import com.Gpro.SpringReclamations.repository.AdministrateurRepository;
import com.Gpro.SpringReclamations.repository.AdministrativeRepository;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;
import com.Gpro.SpringReclamations.repository.GroupeRepository;
import com.Gpro.SpringReclamations.repository.RoleRepository;
import com.Gpro.SpringReclamations.security.jwt.JwtUtils;
import com.Gpro.SpringReclamations.security.jwt.JwtUtilsEtudiant;
import com.Gpro.SpringReclamations.service.EtudiantService;
import com.Gpro.SpringReclamations.service.GroupeService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Etudiant")
public class EtudiantControlleur {
	
	@Autowired
    private EtudiantService etudiantService;
	
	@Autowired
    private GroupeService groupeService;
	
	@Autowired
   // @Qualifier("userauth")
	AuthenticationManager authenticationManager;

	@Autowired
	EtudiantRepository EtudiantRepository;


	@Autowired
	GroupeRepository GroupeRepository;
	
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
    //@Qualifier("pwauath")
	PasswordEncoder encoder;

	@Autowired
	JwtUtilsEtudiant jwtUtilsetudiant;


    // Add new groupe
    @PostMapping("/addEtudiant")
    	public ResponseEntity<?> addEtudiant(@Valid @RequestBody SignupRequest signUpRequest) {
    		if (EtudiantRepository.existsByUsername(signUpRequest.getUsername())) {
    			return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Error: Username is already taken!"));
    		}

    		if (EtudiantRepository.existsByEmail(signUpRequest.getEmail())) {
    			return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Error: Email is already in use!"));
    		}

    		// Create new user's account
    		Etudiant user = new Etudiant(signUpRequest.getCin(),
    							 signUpRequest.getNom(),
    							 signUpRequest.getPrenom(),
    							 signUpRequest.getMatricule(),
    				             signUpRequest.getUsername(),
    							 signUpRequest.getEmail(),
    							 signUpRequest.getGroupe(),
    							 encoder.encode(signUpRequest.getPassword())
    							);

    		Set<String> strRoles = signUpRequest.getRole();
    		Set<Role> roles = new HashSet<>();
    		
    					Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(userRole);


    		user.setRoles(roles); 

    		EtudiantRepository.save(user);

    		return ResponseEntity.ok(new MessageResponse("etudiant registered successfully!"));
    	}
    


    // Get employee by Id
    @GetMapping("/getEtudiantByID/{id}")
    public Etudiant getEtudiantByID(@PathVariable int id) {
        return etudiantService.getEtudiantByID(id);
    }

    // Get groupe by name
    @GetMapping("/getEtudiantByName/{name}")
    public  Etudiant getEtudiantByName(@PathVariable String name) {
        return  etudiantService.getEtudiantByName(name);
    }

    // Update groupe
    @PutMapping("/updateEtudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
    	
        return etudiantService.updateEtudiant(etudiant);
    }

    // Delete groupe
    @DeleteMapping("/deleteEtudiantById/{id}")
    public boolean deleteEtudiantByID(@PathVariable int id) {

        return etudiantService.deleteEtudiantByID(id);
    }

    // Get all groupes
    @GetMapping("/getAllEtudiant")
    public List<Etudiant> getAllEtudiant() {
        return etudiantService.getAllEtudiant();
    }

   
}
