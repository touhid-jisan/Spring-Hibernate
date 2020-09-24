package com.touhid.springdemo.springbootcrud.dao;

import com.touhid.springdemo.springbootcrud.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findEmployees();
    public Employee findEmployee(int theId);
    public void saveEmployee(Employee theEmployee);
    public void deleteEmployee(int theId);

}

