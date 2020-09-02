package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCourses {

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
			
			// create the instructor from db
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Temp Instructor : " + tempInstructor);
			
			// get courses from instructor id 2
			System.out.println("Courses : " + tempInstructor.getCourses());
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
