package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;
import com.touhid.hibernate.demo.entity.Review;
import com.touhid.hibernate.demo.entity.Student;

public class GetMoreCoursesForSpecificDemo {

	public static void main(String[] args) {
		
		// session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			

			
			int studentId = 1;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\n----------------" +"\nTemp Student : "+ tempStudent + "\n-----------------");
			System.out.println("\n----------------" +"\nAlll COurse list : "+ tempStudent.getCourses() + "\n-----------------");
			
			int studentId2 = 2;
			Student tempStudent2 = session.get(Student.class, studentId2);
			
			System.out.println("\n----------------" +"\nTemp Student : "+ tempStudent2 + "\n-----------------");
			System.out.println("\n----------------" +"\nAlll COurse list : "+ tempStudent2.getCourses() + "\n-----------------");
			
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
