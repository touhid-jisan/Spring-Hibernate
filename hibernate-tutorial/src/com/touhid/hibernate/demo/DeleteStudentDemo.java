package com.touhid.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/* CRUD - Delete */
			/* Query 1*/
			int studentId = 6;
			session.beginTransaction();
			/* query 1 for deleting
			 * 
			 * Student myStudent = session.get(Student.class, studentId);
			 * System.out.println("Before deleting " + myStudent);
			 * System.out.println("Deleting Students...");
			 * session.delete(myStudent); 
			 * 
			 */
			/* Query 2*/
			session.createQuery("delete from Student where id=" + studentId).executeUpdate();
			session.getTransaction().commit();
			System.out.println("Deleted");
			
			
			
		} finally {
			factory.close();
		}
	}

}
