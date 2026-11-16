// Learning JPA entity relationships - practice class from Relationship, pushed 2026-10-30
package com.itvedant.basics.one_many;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository <Employee,Integer> {

}


