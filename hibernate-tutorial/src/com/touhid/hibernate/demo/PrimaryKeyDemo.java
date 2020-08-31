package com.touhid.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {
			/* CRUD - CREATE */
			// creating 3 student object
			System.out.println("Creating new student object");
			Student student1 = new Student("Touhid", "Islam", "touhid@gmail.com");
			Student student2 = new Student("ABX", "adsaf", "abx@gmail.com");
			Student student3 = new Student("zxc", "dasd", "zxc@gmail.com");
					
			// create a student object
			session.beginTransaction();
					
			// save the student object
			System.out.println("saving the student...");
			session.save(student1);
			session.save(student2);
			session.save(student3);
					
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
	}
}
