package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;
import com.touhid.hibernate.demo.entity.Review;

public class GetCourseAndReviewDemo {

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
			
			// 1. create a course
			// 2. add some reviews
			// 3. save the course and the leverage the cascade all
			
			Course tempCourse = new Course("CSE 220");

			tempCourse.add(new Review("Tough course but love it"));
			tempCourse.add(new Review("Cool Course"));
			tempCourse.add(new Review("Dumb course"));
			
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReview());
			
			session.save(tempCourse);
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
