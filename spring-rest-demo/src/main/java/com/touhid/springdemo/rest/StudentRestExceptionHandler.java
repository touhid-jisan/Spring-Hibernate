package com.touhid.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

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
		// create a StudentErrorResponse
		StudentErrorResponses error = new StudentErrorResponses();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Bad Request");
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<StudentErrorResponses>(error, HttpStatus.BAD_REQUEST);
	}

}
