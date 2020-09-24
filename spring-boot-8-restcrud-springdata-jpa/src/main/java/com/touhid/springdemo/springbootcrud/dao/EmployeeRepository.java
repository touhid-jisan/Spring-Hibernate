package com.touhid.springdemo.springbootcrud.dao;

import com.touhid.springdemo.springbootcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // thats it no need to write any code like "EmployeeDao or EmployeeDaoImpl"
}
