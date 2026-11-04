// Learning REST controllers in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-17
package com.itvedant.shopkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.shopkart.models.User;
import com.itvedant.shopkart.models.UserDto;
import com.itvedant.shopkart.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto dto) {

        User user = userService.registerUser(dto);

        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
    }
}
