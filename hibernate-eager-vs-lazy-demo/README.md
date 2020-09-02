## **Real -World Project  Requirement**

- If you delete an instructor, **DO NOT** delete the courses

- If you delete a course, **DO NOT** delete the instructor

  

## Development Process: One-to-Many 

1. Prep Work - Define database table

2. Create Course Class

3. Update Instructor Class

4. Create Main App

   

## Step 1: Database Table



> **File 1 : create-db.sql**

```mysql
CREATE TABLE course (
	id int(11) NOT NULL AUTO_INCREMENT,
    title varchar(128)	DEFAULT NULL,
    instructor_id int(11) DEFAULT NULL,
    
    PRIMARY KEY (ID),
    UNIQUE KEY TITLE_UNIQUE (title), # prevent duplicate course title 
    
    # Foreign Key Defining
    KEY FK_INSTRUCTOR_idx (instructor_id),
    CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (instructor_id) REFERENCES instructor(id) 
    ....
    ....
);
```

<img src="/home/boogeyman/Pictures/Kazam_screenshot_00001.png" alt="Kazam_screenshot_00001" style="zoom: 50%;" />



## Step 2: Create Course Class

> **Create Course  Class**

```java
@Entity
@Table(name = "course")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
    
    @Column(name = "title")
    private String title;

    ....
    // constructor, getters, setters
}
```

> **Create Course Class - @ManyToOne**

```java
@Entity
@Table(name = "course")
public class Course {
    
     ....
     @ManyToOne
     @JoinCloumn(name = "instructor_id")
     private Instructor instructor;
     ....
     
    // constructor, getters, setters
}
```



## Step 3: Update Instructor - Reference Courses

> **file: <u>*Instructor.java***</u>

```java
@Entity
@Table(name = "instructor")
public class Instructor {
    ...
    private List<Course> courses;
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
}
```

> **Add @OneToMany annotation**

```java
@Entity
@Table(name = "instructor")
public class Instructor {
    ...
    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
```

> **mappedBy Explanation*****

- mappedBy tells Hibernate
  - Look at the Instructor property in the Course.java Class
  - Use information from the Course.java class @JoinColumn
  - To help find associated courses for instructor

> **Add Support for Cascading in <u>*Instructor.java</u>* class (do not apply cascading delete)**

```java
// Instructor.java

@Entity
@Table(name = "instructor")
public class Instructor {
    ...
    @OneToMany(mappedBy = "instructor",
              cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
```

> **Add support for Cascading in *<u>Course.java</u>* Class (do not apply cascading delete)**

```java
// Course.java

@Entity
@Table(name = "course")
public class Course {
    
     ....
     @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH
                          })
     @JoinCloumn(name = "instructor_id")
     private Instructor instructor;
     ....
     
    // constructor, getters, setters
}
```

> **Add Convenience methods for bi-directional in *<u>Instructor.java</u>* Class**

```java
// Instructor.java

@Entity
@Table(name = "instructor")
public class Instructor {
    ...
    @OneToMany(mappedBy = "instructor",
              cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;
    // getters , setters
    
    // add convenience methods for bi-directional relationship
    public void add (Course tempCourse) {
        if(courses == null) {
            courses = new ArrayList<>();
        }
        
        // bi-directional link starts
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
        // bi-directional link ends
    }
}
```



> ### FInal Schema of Dtatbase

![Kazam_screenshot_00002](/home/boogeyman/Pictures/Kazam_screenshot_00002.png)

## Step 4: Add Main App

> **CreateInstructorDemo.java**

```java
public class CreateInstructorDemo {

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
			// create the objects  
			Instructor tempInstructor = new Instructor("Shahriar", "Anik", "Anik@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/Anik", "teaching");
			
			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			session.save(tempInstructor); // this will also save the instructor_detail object because of CascadeType.ALL
			session.getTransaction().commit();
			System.out.println("DONE!!!!");
		}finally {
			session.close(); // clean up the code
			factory.close();
		}
	}
}
```

> **GetInstructorCourses.java**

```java
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

```

> **DeleteCourseDemo.java**

```java
public class DeleteCourseDemo {

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
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			session.delete(tempCourse);
			
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
```

