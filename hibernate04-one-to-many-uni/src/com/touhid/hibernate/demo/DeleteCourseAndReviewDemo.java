package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;
import com.touhid.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewDemo {

	public static void main(String[] args) {
		
		// session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			session.delete(tempCourse);
			
			session.save(tempCourse);
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
