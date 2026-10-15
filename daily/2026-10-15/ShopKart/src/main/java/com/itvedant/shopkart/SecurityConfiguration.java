// Learning a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-15


package com.itvedant.shopkart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
    public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

//        http.csrf(csrf -> csrf.disable());
    	
    	http.csrf(csrf -> csrf.disable());
    	
    	http.authorizeHttpRequests(auth ->
    		auth.requestMatchers(HttpMethod.POST, "/users/register").permitAll()
    		.requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
    		.requestMatchers(HttpMethod.PUT, "/products").hasRole("ADMIN")	 
    		.requestMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN")	 
    		.anyRequest()
    		.permitAll()
    	)
    	.httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
}
