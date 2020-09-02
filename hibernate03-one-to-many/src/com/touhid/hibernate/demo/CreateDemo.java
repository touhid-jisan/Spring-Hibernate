package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			// create the objects  
			Instructor tempInstructor = new Instructor("Touhid", "Jisan", "Jisan@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/Jisan", "programmings");
			
			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			session.save(tempInstructor); // this will also save the instructor_detail object because of CascadeType.ALL
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			factory.close();
		}
		
	}

}
