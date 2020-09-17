package com.touhid.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) { 
		
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and map/convert to Java POJO:
			// data/sample-lite.json
			Student theStudent = mapper.readValue(new File("data/sample-lite.json"), Student.class);
			
			Teacher theTeacher = mapper.readValue(new File("data/sample-full.json"), Teacher.class);
			
			// print first name last name
			System.out.println("First name: " + theStudent.getFirstName() );
			System.out.println("Last name: " + theStudent.getLastName() );
			System.out.println("Id: " + theStudent.getId() );
			
			
			System.out.println("First name: " + theTeacher.getFirstName() );
			System.out.println("Last name: " + theTeacher.getLastName() );
			System.out.println("Id: " + theTeacher.getId()  );
			System.out.println("Address : "+theTeacher.getAddress());
			
			for(String tempLang : theTeacher.getLanguages()) {
				System.out.print(tempLang + ", ");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
