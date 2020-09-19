package com.touhid.springdemo.rest;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touhid.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;

	// define @PostConstruct to load the student data ... only once
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Touhid", "Jisan"));
		theStudents.add(new Student("Shah", "Anik"));
		theStudents.add(new Student("Alamin", "Islam"));

	}

	// define end point for "/students" - return list of student
	@GetMapping("/students")
	public List<Student> getStudents() {

		return theStudents;
	}

	// define end point for "students/{studentId}" - return single student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		if (studentId >= theStudents.size() || studentId < 0) {
			throw new StudentNotFoundException("Student not found -" + studentId);
		}
		// just index into the list... keeping to simple for now
		return theStudents.get(studentId);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponses> handlerException(StudentNotFoundException e) {

		// create a StudentErrorResponse
		StudentErrorResponses error = new StudentErrorResponses();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// add annother exception handler // to catch any exception (catch alll)
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponses> handlerException(Exception e) {
//		create a StudentErrorResponse
		StudentErrorResponses error = new StudentErrorResponses();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Bad Request");
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentErrorResponses>(error, HttpStatus.BAD_REQUEST);
	}

}
