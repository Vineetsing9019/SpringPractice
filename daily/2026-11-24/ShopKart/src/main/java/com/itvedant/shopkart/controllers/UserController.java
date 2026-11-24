// Learning REST controllers in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.shopkart.JwtTokenUtil;
import com.itvedant.shopkart.models.LoginDto;
import com.itvedant.shopkart.models.Token;
import com.itvedant.shopkart.models.User;
import com.itvedant.shopkart.models.UserDto;
import com.itvedant.shopkart.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto dto) {
		User user = userService.registerUser(dto);
		return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto dto) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							dto.getEmail(), dto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			User authUser = (User) authentication.getPrincipal();
//			System.out.println("User Controller:"+user);
			Token t = new Token();
			t.jwtToken = jwtTokenUtil.generateToken(authUser);
			return new ResponseEntity<>(t,HttpStatus.OK);
		}
		catch(BadCredentialsException e) {
			return new ResponseEntity<>("Error logging in !!",HttpStatus.UNAUTHORIZED);
		}
	}
}

