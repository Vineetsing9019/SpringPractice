// Learning a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-31

package com.itvedant.shopkart;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itvedant.shopkart.models.User;
import com.itvedant.shopkart.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Get user from database using email
        User dbUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Invalid email address: " + email
                ));

        // Convert database roles into Spring Security authorities
        return org.springframework.security.core.userdetails.User
                .withUsername(dbUser.getEmail())
                .password(dbUser.getPassword())
                .authorities(
                        dbUser.getRoles()
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

