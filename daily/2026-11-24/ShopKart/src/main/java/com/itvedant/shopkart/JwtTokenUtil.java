// Learning a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-11-24
package com.itvedant.shopkart;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.itvedant.shopkart.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenUtil {
	
	//generate the below key once at begining 
//	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final String jwtSecret = "YhPCzFiJ5rYtjDgASrOBsUS9r05ZK8pR7bIbhVR7ZIs="; // key
	private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	private final String jwtIssuer = "example.com";// issuer, its optional
		
//	public JwtTokenUtil() {
//		System.out.println("KEY: "+Encoders.BASE64.encode(key.getEncoded()));
//		System.out.println();
//	}
	
	public String generateToken(User user) {
//		String id = new String(user.getId());
		return Jwts.builder()
			    // 1. Set the core unique identifier as the subject
			    .subject(String.valueOf(user.getId())) 
			    
			    // 2. Add individual, structured claims for extra data
			    .claim("username", user.getUsername())
			    .claim("email", user.getEmail())
			    .claim("roles", user.getRoles()) 
			    
			    // 3. Metadata and security
			    .issuer(jwtIssuer)
			    .issuedAt(new Date())
			    .expiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))) // 1 hour
			    .signWith(key)
			    .compact();	
	}
	
	public String getUserid(String token) {
		Claims claims = Jwts.parser()
	            .verifyWith(key) // Replaces setSigningKey(key)
	            .build()
	            .parseSignedClaims(token) // Replaces parseClaimsJws(token)
	            .getPayload();
		
		return claims.getSubject();
	}
	
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
	            .verifyWith(key) // Replaces setSigningKey(key)
	            .build()
	            .parseSignedClaims(token) // Replaces parseClaimsJws(token)
	            .getPayload(); // Replaces getBody()

	    return (String)claims.get("username");
	}
	
	public boolean validate(String token) {
		try {
	        Jwts.parser()
	            .verifyWith(key)             // Replaces setSigningKey(key)
	            .build()
	            .parseSignedClaims(token);   // Replaces parseClaimsJws(token)
	        return true;
	    }
		catch (SignatureException e) {
			System.out.println("Invalid Jwt signature - %s"+e.getMessage());
		}
		catch (MalformedJwtException e) {
			System.out.println("Invalid Jwt token - %s"+e.getMessage());
		}
		catch (ExpiredJwtException e) {
			System.out.println("Expired Jwt token - %s"+e.getMessage());
		}
		catch (UnsupportedJwtException e) {
			System.out.println("Unsupported Jwt token - %s"+e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println("Jwt claims string is empty - %s"+e.getMessage());
		}
		return false;
	}
}

