# One To Many Mapping - Uni Directional

- A course can have many reviews 

  - It is Uni-Directional

- If we delete a course, also delete the reviews

- Reviews without a course, have no meaning

  <img src="https://githubpictures.000webhostapp.com/pictures/OneToManyUniDirectional1.png" style="zoom: 67%;" />

<img src="https://githubpictures.000webhostapp.com/pictures/@OneToManyTableRelationship.png" style="zoom: 70%;" />

<img src="https://githubpictures.000webhostapp.com/pictures/Diagram.png" style="zoom: 33%;" />



## Development Process: 

> ### Step 1: Define database tables		

```
file: create-de.sql
```

```SQL

CREATE TABLE review (
	id int(11) NOT NULL AUTO_INCREMENT,
    comment varchar(256) DEFAULT NULL,
    course_id int(11) DEFAULT NULL,
    PRIMARY KEY (id),
    /* foreign key relationship */
    KEY FK_COURSE_ID_idx (course_id),
    
    CONSTRAINT FK_COURSE
    FOREIGN KEY (course_id)
    REFERENCE course (id)
    ...
);
```



![](https://githubpictures.000webhostapp.com/pictures/ERDIGRAM1.png)



> ### Step 2: Create <u>Review</u> Class

```
file: Review.java
```

```java
@Entity
@Table(name="review")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="String")
    private String comment;  
}
```

> ### Step 3: Update <u>Course</u> Class

```
file: Course.java
```

```java
@Entity
@Table(name="course")
public class Course {
	....
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
	private List<Reviews> reviews;
	....
        
    // constructor, setters, getters
    // add convenience methods for adding reviews
    public void add(Review tempReview) {
        if(reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(tempReview);
    }
}
```



> ### Step 4: Create Main App

```
Creating Course and Reviews
file: CreateCourseAndReview.java
```

```java
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
```

------

------



```
Getting Course and Reviews
file: GetCourseAndReview.java
```

```java
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
			
	// 1. get the course
	// 2. print the course
	// 3. print the course reviews
	
    int theId = 10;
	Course tempCourse = session.get(Course.class, theId);
			
	System.out.println("TempCourse : " + tempCourse);
	System.out.println("Reviews : "  + tempCourse.getReview());
			
	session.getTransaction().commit();
	System.out.println("DONE!!!!");

}finally {
	session.close(); // clean up the code
	factory.close();
}
```

------

------

```
Deleting Course with Review
file: DeleteCourseAndReviewDemo.java
```

```java
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
```

