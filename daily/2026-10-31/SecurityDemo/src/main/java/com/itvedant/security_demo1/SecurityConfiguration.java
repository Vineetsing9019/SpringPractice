// Learning Spring Security - practice class from SecurityDemo, pushed 2026-10-31
package com.itvedant.security_demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(auth ->
	        auth.requestMatchers(HttpMethod.POST, "/products").authenticated()
	        .requestMatchers(HttpMethod.DELETE, "/products/**").authenticated()
	            .anyRequest().permitAll()
	    )
	    .httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
