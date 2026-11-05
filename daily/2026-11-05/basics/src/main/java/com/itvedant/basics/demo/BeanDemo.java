// Learning Spring Boot basics - practice class from basics, pushed 2026-10-19
package com.itvedant.basics.demo;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//tell spring to check and execute all @Bean methods
public class BeanDemo {
	
	@Bean //spring will execute @Bean method, sores the 
	//returned bean in spring container for memory management(sharing
	public Scanner getScanner() {
		System.out.println("Scanner bean is created");
		Scanner sc = new Scanner(System.in);
		return sc;
		
	}

}

