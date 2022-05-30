package com.Gpro.SpringReclamations.controlleur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.model.ERole;
import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.model.Role;
import com.Gpro.SpringReclamations.playload.request.LoginRequest;
import com.Gpro.SpringReclamations.playload.request.SignupRequest;
import com.Gpro.SpringReclamations.playload.response.JwtResponse;
import com.Gpro.SpringReclamations.playload.response.MessageResponse;
import com.Gpro.SpringReclamations.repository.AdministrateurRepository;
import com.Gpro.SpringReclamations.repository.EnseignantRepository;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;
import com.Gpro.SpringReclamations.repository.RoleRepository;
import com.Gpro.SpringReclamations.security.jwt.JwtUtils;
import com.Gpro.SpringReclamations.security.jwt.JwtUtilsEnseignant;
import com.Gpro.SpringReclamations.security.jwt.JwtUtilsEtudiant;
import com.Gpro.SpringReclamations.security.services.EnseignantDetailsImpl;
import com.Gpro.SpringReclamations.security.services.EtudiantDetailsImpl;
import com.Gpro.SpringReclamations.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthControlleur {

	@Autowired
	//@Qualifier("userauth")
	AuthenticationManager authenticationManager;

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	EnseignantRepository enseignantRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
   // @Qualifier("pwauath")
	PasswordEncoder encoder;

	@Autowired
	JwtUtilsEtudiant jwtUtilsetudiant;
	
	@Autowired
	JwtUtilsEnseignant jwtUtilsEnseignant;

	@PostMapping("/signinuser")
	public ResponseEntity<?> authenticateEtudiant(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtilsetudiant.generateJwtToken(authentication);
		
		EtudiantDetailsImpl userDetails = (EtudiantDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}	
	@PostMapping("/signinEnseignant")
	public ResponseEntity<?> authenticateEnseignant(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtilsEnseignant.generateJwtToken(authentication);
		
		EnseignantDetailsImpl userDetails = (EnseignantDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}	
}
