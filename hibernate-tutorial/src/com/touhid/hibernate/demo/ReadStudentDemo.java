package com.touhid.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/********************** CRUD - CREATE Starts**********************/
			// use the session object to save java object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Dog", "tom", "tom@gmail.com");
			
			// create a student object
			session.beginTransaction();
			
			// save the student object
			System.out.println("saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			/********************** CRUD - CREATE Ends **********************/
		
			
			
			/********************** CRUD - READ Starts**********************/
			System.out.println("Saved Student id : " + tempStudent.getId());
			
			// get a new session and transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id : primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get Complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			/********************** CRUD - READ Ends**********************/
		} finally {
			factory.close();
		}
	}

}
