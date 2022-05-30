package com.Gpro.SpringReclamations.controlleur;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.model.ERole;
import com.Gpro.SpringReclamations.model.Role;
import com.Gpro.SpringReclamations.playload.request.LoginRequest;
import com.Gpro.SpringReclamations.playload.request.SignupRequest;
import com.Gpro.SpringReclamations.playload.response.JwtResponse;
import com.Gpro.SpringReclamations.playload.response.MessageResponse;
import com.Gpro.SpringReclamations.repository.AdministrateurRepository;
import com.Gpro.SpringReclamations.repository.RoleRepository;
import com.Gpro.SpringReclamations.security.jwt.JwtUtils;
import com.Gpro.SpringReclamations.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AdministrateurControlleur {
	
	@Autowired
    //@Qualifier("adminauth")
	AuthenticationManager authenticationManager;

	@Autowired
	AdministrateurRepository administrateurRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
   // @Qualifier("pwadmin")
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (administrateurRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (administrateurRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Administrateur user = new Administrateur(signUpRequest.getCin(),
							 signUpRequest.getNom(),
							 signUpRequest.getPrenom(),
				             signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword())
							);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
					Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
			


		user.setRoles(roles);
		administrateurRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("admin registered successfully!"));
	}
	
}
