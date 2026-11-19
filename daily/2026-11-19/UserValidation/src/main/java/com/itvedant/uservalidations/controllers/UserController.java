// Learning REST controllers in bean validation in Spring - practice class from UserValidation, pushed 2026-10-16
package com.itvedant.uservalidations.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.uservalidations.models.User;
import com.itvedant.uservalidations.models.UserDto;
import com.itvedant.uservalidations.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("")  // endpoint"/users"
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto dto){
		userService.registerUser(dto);
		return new ResponseEntity<>(" user registered sucessfully", HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllUsers(){
		List allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	//http://localhost:8080/users/2 - delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable int id) {
			userService.deleteUserById(id);
		return new ResponseEntity<>("user deleted sucessfully!!", HttpStatus.NO_CONTENT);
		
	}
	
@PutMapping("/{id}")
public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody @Valid UserDto dto){
	User updatedUser = userService.UpdatedUserById(id, dto);
	return new ResponseEntity<>(updatedUser,HttpStatus.OK);
}

	
	
	
	
	
}
	
	
