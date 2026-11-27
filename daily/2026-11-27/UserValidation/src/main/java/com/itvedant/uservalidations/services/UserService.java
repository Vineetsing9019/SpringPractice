// Learning the service layer in bean validation in Spring - practice class from UserValidation, pushed 2026-10-19
package com.itvedant.uservalidations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.uservalidations.models.User;
import com.itvedant.uservalidations.models.UserDto;
import com.itvedant.uservalidations.repositories.UserRepository;

@Service

public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(UserDto dto) {
		// if password  mismatch throw exception
		if(!dto.getPassword().equals(dto.getConfirmPassword())) {
			throw new RuntimeException("Password and Confirm Password do not match");
		}
		
		// create user object
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		//save user object 
		User dbUser = userRepository.save(user);
		return dbUser;
		
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public User getUserById(int id) {
		User requiredUser = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"PLEASE PROV9DE VALID USER ID "));
				return requiredUser;
		
		
	}
	public  void deleteUserById(int id) {
		
		//check if the id is  valid
		getUserById(id);
		//delete
		userRepository.deleteById(id);
		
		
	}
	public User UpdatedUserById(int id,  UserDto dto) {
		// if id is valid
		getUserById(id);
		
		
		// user object creation
		User user = new User();
		user.setId(id);
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		
		//save 
		User updateUser = userRepository.save(user);
		return updateUser;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
	

