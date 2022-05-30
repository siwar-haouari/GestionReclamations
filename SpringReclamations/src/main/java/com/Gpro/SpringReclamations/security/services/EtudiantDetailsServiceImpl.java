package com.Gpro.SpringReclamations.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Gpro.SpringReclamations.model.Etudiant;
import com.Gpro.SpringReclamations.repository.EtudiantRepository;

@Service
public class EtudiantDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	EtudiantRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Etudiant user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username:"+username));

		return EtudiantDetailsImpl.build(user);
	}

}
