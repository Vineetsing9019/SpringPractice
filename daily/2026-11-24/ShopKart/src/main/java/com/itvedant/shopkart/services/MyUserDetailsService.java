// Learning the service layer in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.shopkart.models.User;
import com.itvedant.shopkart.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// db, get the user based on email
		User dbUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please enter the valid email address"));
		// we need to create the UserDetails object... 
		UserDetails userDetails = new User(
				dbUser.getId(),
				dbUser.getUsername(),
				dbUser.getEmail(),
				dbUser.getPassword(),
				dbUser.getMobile(),
				dbUser.getRoles(),
				dbUser.getRoles()
				.stream().map(role -> new SimpleGrantedAuthority(role))
				.toList()				
				);
		return userDetails;
	}

}

