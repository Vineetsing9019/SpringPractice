// Learning Spring Data REST with validation - practice class from UserSpringDataRestDemoUserValidation-1, pushed 2026-10-26
package com.itvedant.basics.userprojection;

import org.springframework.data.rest.core.config.Projection;

import com.itvedant.basics.models.User;

@Projection(types = {User.class})
public interface UserProjection {
	
	//getters 
	public int getAge();
	public int getId();
	public String getUsername();
	public String getEmail();
}

