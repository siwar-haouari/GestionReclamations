package com.Gpro.SpringReclamations.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Gpro.SpringReclamations.model.Administrateur;
import com.Gpro.SpringReclamations.repository.AdministrateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AdministrateurRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Administrateur user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username:"+username));

		return UserDetailsImpl.build(user);
	}

}
