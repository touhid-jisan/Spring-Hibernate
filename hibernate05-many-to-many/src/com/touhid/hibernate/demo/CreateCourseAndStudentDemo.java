package com.touhid.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.touhid.hibernate.demo.entity.Course;
import com.touhid.hibernate.demo.entity.Instructor;
import com.touhid.hibernate.demo.entity.InstructorDetail;
import com.touhid.hibernate.demo.entity.Review;
import com.touhid.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

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
			
			// create course
			Course tempCourse = new Course("CSE-110");
			session.save(tempCourse);
			
			System.out.println("saved course : " + tempCourse);
			
			Student tempStuent1 = new Student("Touhid", "Jisan", "touhid@gmail.com");
			Student tempStuent2 = new Student("Shahriar", "Anik", "shah@gmail.com");
			
			tempCourse.addStudent(tempStuent1);
			tempCourse.addStudent(tempStuent2);
			session.save(tempStuent1); 
			session.save(tempStuent2);  
			System.out.println("\n------------------\n" + tempCourse.getStudents() + "\n-------------------");
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
		
	}

}
