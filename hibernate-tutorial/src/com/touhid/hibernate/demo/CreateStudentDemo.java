package com.touhid.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save java object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Touhid", "Islam", "touhid@gmail.com");
			
			// create a student object
			session.beginTransaction();
			
			// save the student object
			System.out.println("saving the student...");
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
