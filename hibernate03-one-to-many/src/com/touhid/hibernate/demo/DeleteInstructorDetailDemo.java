package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			int theId = 5;
			
			// get the instructor detail object
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail 
			System.out.println("tempInstruc torDetail : " + tempInstructorDetail);
			
			/*
			 * // print the associated instructor
			 * System.out.println("associated instructor : " +
			 * tempInstructorDetail.getInstuctor());
			 */
			
			// let's delete instructor detail
			System.out.println("deleting tempInstructorDetail : " + tempInstructorDetail);
			
			tempInstructorDetail.getInstuctor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
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
