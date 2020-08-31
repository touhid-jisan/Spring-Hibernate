package com.touhid.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/* CRUD - READ */
			
			// create a student object
			session.beginTransaction();
			
			//Query 1: query students
			System.out.println("\nQuery 1");
			List<Student> theStudents = 
					session
					.createQuery("from Student")
					.getResultList();
			displayStudents(theStudents);
			

			//Query 1: query student lastName = 'Tom'
			System.out.println("\nQuery 2");
			theStudents = session
					.createQuery("from Student s where s.lastName='adsaf'")
					.getResultList();
			displayStudents(theStudents);
			
			//Query 3: query student firstName = 'Touhid' OR lastName='tom'
			System.out.println("\nQuery 3");
			theStudents = session
					.createQuery("from Student where firstName='Touhid' OR lastName='tom'")
					.getResultList();
			displayStudents(theStudents);

			
			//Query 4: query student where email LIKE='%.com'
			System.out.println("\nQuery 4");
			theStudents = session
					.createQuery("from Student where email LIKE '%.com'")
					.getResultList();
			displayStudents(theStudents);

			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
