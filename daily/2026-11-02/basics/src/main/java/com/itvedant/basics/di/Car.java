// Learning Spring Boot basics - practice class from basics, pushed 2026-10-15
package com.itvedant.basics.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

	
//	 1. constructor injection
//	 private Engine engine; //null 1st type 

	
	 
//	 public Car(Engine engine) {
//		 this.engine = engine;
//		System.out.println("Car bean has been configured with engine dependency");
//	 }
//	
//	
	
/*	
//	2. setter injection
	private Engine engine;  
	 @Autowired // optional
	 public void setEngine(Engine engine) {
		 System.out.println("Setter DI");
			this.engine = engine;
			 this.startCar();
		}
	 
	 
	public void startCar() {
		engine.startEngine();
	 System.out.println("Car is started");
	}

	
	
	
}
*/
	
	
	// Field injection
	 private  Engine engine;
} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

