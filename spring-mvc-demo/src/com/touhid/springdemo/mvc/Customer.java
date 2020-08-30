package com.touhid.springdemo.mvc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {
	
	private String firstName;
	
	@NotNull(message="is required")
	@Size(min=2, message="is required")
	private String lastName;

	@NotNull(message="is required")
	@Min(value=0, message="Value Must Be Gretter Than or Equal to 0")
	@Max(value=10, message="Value Must Be Less Than or Equal to 10")
	private Integer idNumber;
	
	@Pattern(regexp="^[a-zA-z0-9]{4,5}", message="Not valid (regexp=\"^[a-zA-z0-9]{4,5})")
	private String postalCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}
	

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
