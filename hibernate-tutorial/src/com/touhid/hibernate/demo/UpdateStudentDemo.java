package com.touhid.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/* CRUD - Update */
			/* Query 1*/
			int studentId = 3;
			
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Before updating " + myStudent);
			System.out.println("Updating Students");
			
			myStudent.setLastName("Bob");
			
			session.getTransaction().commit();

			System.out.println("Updated");
			
			/* Query 2*/
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("Updating email for all students");
			session.createQuery("update Student set email='nomail@edu.com'")
				.executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Updated all students");
			
		} finally {
			factory.close();
		}
	}

}
