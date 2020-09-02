package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		// session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			session.delete(tempCourse);
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// handles connection leak
			session.close();
			factory.close();
		}
		
	}

}
