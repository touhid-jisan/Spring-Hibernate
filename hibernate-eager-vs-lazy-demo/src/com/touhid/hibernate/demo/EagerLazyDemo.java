package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			// option 2: Hibernate query with HQL	
			// create the instructor from db 
			int theId = 2;
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id =:theInstructorId", Instructor.class);
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Temp Instructor : " + tempInstructor);
			
			
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
