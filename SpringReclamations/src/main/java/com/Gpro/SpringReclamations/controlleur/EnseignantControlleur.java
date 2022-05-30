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
import org.springframework.web.bind.annotation.RestController;

import com.Gpro.SpringReclamations.model.ERole;
import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.model.Role;
import com.Gpro.SpringReclamations.playload.request.SignupRequest;
import com.Gpro.SpringReclamations.playload.response.MessageResponse;
import com.Gpro.SpringReclamations.repository.EnseignantRepository;
import com.Gpro.SpringReclamations.repository.RoleRepository;
import com.Gpro.SpringReclamations.security.jwt.JwtUtilsEnseignant;
import com.Gpro.SpringReclamations.service.EnseignantService;





@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Enseignant")
public class EnseignantControlleur {
	
	@Autowired
    private EnseignantService enseignantService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EnseignantRepository EnseignantRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtilsEnseignant JwtUtilsenseignant;


    // Add new groupe
    @PostMapping("/addEnseignant")
    	public ResponseEntity<?> addEnseignant(@Valid @RequestBody SignupRequest signUpRequest) {
    		if (EnseignantRepository.existsByUsername(signUpRequest.getUsername())) {
    			return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Error: Username is already taken!"));
    		}

    		if (EnseignantRepository.existsByEmail(signUpRequest.getEmail())) {
    			return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Error: Email is already in use!"));
    		}

    		// Create new user's account
    		Enseignant user = new Enseignant(
    				             signUpRequest.getCin(),
    							 signUpRequest.getNom(),
    							 signUpRequest.getPrenom(),
    							 signUpRequest.getTypeContrat(),
    							 signUpRequest.getSpecialite(),
    				             signUpRequest.getUsername(),
    							 signUpRequest.getEmail(),
    							 encoder.encode(signUpRequest.getPassword())
    							);

    		Set<String> strRoles = signUpRequest.getRole();
    		Set<Role> roles = new HashSet<>();
    		
    					Role userRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
    							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    					roles.add(userRole);


    		user.setRoles(roles); 

    		EnseignantRepository.save(user);

    		return ResponseEntity.ok(new MessageResponse("enseignant registered successfully!"));
    	}
    


    // Get employee by Id
    @GetMapping("/getEnseignantByID/{id}")
    public Enseignant getEnseignantByID(@PathVariable int id) {
        return enseignantService.getEnseignantByID(id);
    }

    // Get groupe by name
    @GetMapping("/getEnseignantByName/{name}")
    public  Enseignant getEnseignantByName(@PathVariable String name) {
        return  enseignantService.getEnseignantByName(name);
    }

    // Update groupe
    @PutMapping("/updateEnseignant")
    public Enseignant updateEnseignant(@RequestBody Enseignant Enseignant) {
        return enseignantService.updateEnseignant(Enseignant);
    }

    // Delete groupe
    @DeleteMapping("/deleteEnseignantByID/{id}")
    public boolean deleteEnseignantByID(@PathVariable int id) {
        return enseignantService.deleteEnseignantByID(id);
    }

    // Get all groupes
    @GetMapping("/getAll")
    public List<Enseignant> getAllEnseignant() {
        return enseignantService.getAllEnseignant();
    }

   
}
