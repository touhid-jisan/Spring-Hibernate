package com.touhid.springdemo.springbootcrud.rest;

import com.touhid.springdemo.springbootcrud.dao.EmployeeDAO;
import com.touhid.springdemo.springbootcrud.entity.Employee;
import com.touhid.springdemo.springbootcrud.service.EmployeeService;
import org.dom4j.util.AttributeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getEmployess() {
		return employeeService.findEmployees();
	}

	@GetMapping("/employees/{theId}")
	public Employee getEmployee(@PathVariable int theId) {
		Employee theEmployee = employeeService.findEmployee(theId);
		if(theEmployee == null) {
			throw new RuntimeException("Employee not found" + theId);
		}
		return  theEmployee;
	}
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		// also jst in case the pass an id in JSON set id to 0
		// this is to force a save of new item.. instead of update
		theEmployee.setId(0);
		employeeService.saveEmployee(theEmployee);
		return  theEmployee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}


	@DeleteMapping("/employees/{theId}")
	public Employee deleEmployee(@PathVariable int theId) {

		Employee tempEmployee = employeeService.findEmployee(theId);
		if(tempEmployee == null) {
			throw new RuntimeException("ID not found");
		}
		employeeService.deleteEmployee(theId);
		return tempEmployee;
	}

}
