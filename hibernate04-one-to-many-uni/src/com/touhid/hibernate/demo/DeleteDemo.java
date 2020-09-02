package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class,theId);
			if(tempInstructor != null) {
				// it will also delete the instructor_detail information based on theId
				// beacuse of CascadeType.ALL 
				session.delete(tempInstructor);
			}
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			factory.close();
		}
		
	}

}
