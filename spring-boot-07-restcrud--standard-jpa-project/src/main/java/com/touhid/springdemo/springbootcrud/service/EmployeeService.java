package com.touhid.springdemo.springbootcrud.service;

import com.touhid.springdemo.springbootcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findEmployees();

    public Employee findEmployee(int theId);

    public void saveEmployee(Employee theEmployee);

    public void deleteEmployee(int theId);

}
