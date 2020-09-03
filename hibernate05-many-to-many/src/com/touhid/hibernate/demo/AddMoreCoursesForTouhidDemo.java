package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;
import com.touhid.hibernate.demo.entity.Review;
import com.touhid.hibernate.demo.entity.Student;

public class AddMoreCoursesForTouhidDemo {

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
			
			// 1. get the student touhid from database
			// 2. create more courses
			// 3. add student to courses
			// 4. save the courses 
			
			int studentId = 1;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\n----------------" + "Before adding more courses : " + tempStudent.getCourses() + "\n----------------");
			
			Course tempCourse1 = new Course("BUS-101");
			Course tempCourse2 = new Course("MAT-110");
			Course tempCourse3 = new Course("CSE-111");

			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			tempCourse3.addStudent(tempStudent);
			
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
