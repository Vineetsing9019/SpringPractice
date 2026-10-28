// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-28
package com.itvedant.relationships.one_many;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.itvedant.basics.one_many.Employee;

@Projection(types = {Employee.class})
public interface DepartmentProjection {
	
	public int getId() ;
	public String getName();
	public List<Employee> getAllEmployee();

}
