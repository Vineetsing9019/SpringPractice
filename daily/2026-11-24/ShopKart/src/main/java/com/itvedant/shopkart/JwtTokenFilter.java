// Learning a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.itvedant.shopkart.services.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		 String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		 
		 if (! StringUtils.hasText(header) || ! header.startsWith("Bearer ")) {
			 filterChain.doFilter(request, response);
			 return ;
		 }
		 // on login , as header and bearer token is missing, request will be returned from here itself
		 
		 String token = header.split(" ")[1].trim();
		 
		 if (! jwtTokenUtil.validate(token)) {
			 filterChain.doFilter(request, response);
			 return;
		 }
		 
		 UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsername(token));
		 
		 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		 
		 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 filterChain.doFilter(request, response);
	}
}

