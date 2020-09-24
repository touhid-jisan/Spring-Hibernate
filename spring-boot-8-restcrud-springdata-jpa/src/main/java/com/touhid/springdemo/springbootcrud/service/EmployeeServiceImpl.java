package com.touhid.springdemo.springbootcrud.service;

import com.touhid.springdemo.springbootcrud.dao.EmployeeRepository;
import com.touhid.springdemo.springbootcrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository thEmployeeRepository) {
        employeeRepository = thEmployeeRepository;
    }

    @Override
    public List<Employee> findEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployee(int theId) {

        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if(result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find any employee ID + " +theId );
        }
        return theEmployee;

    }

    @Override
    public void saveEmployee(Employee theEmployee) {

        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteEmployee(int theId) {

        employeeRepository.deleteById(theId);
    }
}
