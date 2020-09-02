package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			// create some courses 
			Course courseOne = new Course("BBA 101");
			Course courseTwo = new Course("MGT 101");
			Course courseThree= new Course("FIN 101");
			
			// add courses to instructor 
			tempInstructor.add(courseOne);
			tempInstructor.add(courseTwo);
			tempInstructor.add(courseThree);
			
			// save the courses - (add function in course.java)
			session.save(courseOne);
			session.save(courseTwo);
			session.save(courseThree);

			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
