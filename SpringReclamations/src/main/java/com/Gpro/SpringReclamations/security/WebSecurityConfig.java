package com.Gpro.SpringReclamations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Gpro.SpringReclamations.security.jwt.AuthEntryPointJwt;
import com.Gpro.SpringReclamations.security.jwt.AuthTokenFilter;
import com.Gpro.SpringReclamations.security.jwt.EnseignantTokenFilter;
import com.Gpro.SpringReclamations.security.jwt.EtudiantTokenFilter;
import com.Gpro.SpringReclamations.security.services.EnseignantDetailsServiceImpl;
import com.Gpro.SpringReclamations.security.services.EtudiantDetailsServiceImpl;
import com.Gpro.SpringReclamations.security.services.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	EtudiantDetailsServiceImpl etudiantDetailsService;
	
	@Autowired
	EnseignantDetailsServiceImpl enseignantDetailsService;

	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public EtudiantTokenFilter authenticationJwtTokenFilter2() {
		return new EtudiantTokenFilter();
	}
	@Bean
	public EnseignantTokenFilter authenticationJwtTokenFilter3() {
		return new EnseignantTokenFilter();
	}




	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		authenticationManagerBuilder.userDetailsService(etudiantDetailsService).passwordEncoder(passwordEncoder());
		authenticationManagerBuilder.userDetailsService(enseignantDetailsService).passwordEncoder(passwordEncoder());



	}
		
	@Bean
	@Override
    //@Qualifier("adminauth")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
    //@Qualifier("pwadmin")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/auth/**").permitAll()
			//etudiant
			.antMatchers("/Administrative/addAdministrative").hasAnyAuthority("ROLE_ETUDIANT")

			.antMatchers("/Pedagogique/addPedagogique").hasAnyAuthority("ROLE_ETUDIANT")

			//enseignant
	        .antMatchers(HttpMethod.GET,"/Pedagogique/getPedagogiqueByEnseignantId/*").permitAll()

			
			//etudiant+admin
			.antMatchers("/Enseignant/getAll").hasAnyAuthority("ROLE_ETUDIANT","ROLE_ADMIN")
			.antMatchers("/Administrative/getAll").hasAnyAuthority("ROLE_ADMIN","ROLE_ETUDIANT")

			//admin
			.antMatchers("/Administrative/***").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/Etudiant/***").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/Enseignant/***").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/Pedagogique/***").hasAnyAuthority("ROLE_ADMIN")

			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(authenticationJwtTokenFilter2(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(authenticationJwtTokenFilter3(), UsernamePasswordAuthenticationFilter.class);


	}

}
