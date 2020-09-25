package com.touhid.springdemo.springbootcrud.dao;

import com.touhid.springdemo.springbootcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // thats it no need to write any code like "EmployeeDao or EmployeeDaoImpl"
}
