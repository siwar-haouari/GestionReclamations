package com.Gpro.SpringReclamations.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Gpro.SpringReclamations.model.Enseignant;
import com.Gpro.SpringReclamations.repository.EnseignantRepository;

@Service
public class EnseignantDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	EnseignantRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Enseignant user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return EnseignantDetailsImpl.build(user);
	}

}
