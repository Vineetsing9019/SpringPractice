// Learning Spring Boot basics - practice class from basics, pushed 2026-10-16
package com.itvedant.basics.di;

import org.springframework.stereotype.Component;

@Component
public class Engine {
	
	public Engine() {
		System.out.println("Engine bean created");
	}

	public void startEngine() {
		System.out.println("Engine Started");
	}

}

