# **Fetch Types: Eager vs Lazy Loading**

- ###### **When we fetch / retrieve data, should we retrieve everything?**

  - **Eager** will retrieve everything
  - **Lazy** will retrieve on request

  #### **Eager Loading**

  - **Eager** loading will load all dependent entities 
    -  Load instructor and all of their courses at onces
  - In our app, if we are searching for a course by keyword we **only want a list of matching course**. But **Eager Loading** would still **load all students** for each course. not good!!!

*********<u>Prefer Lazy loading instead of Eager Loading</u>*********

#### 	Lazy Loading

- - Lazy Loading will load the main entity first.
  - Load dependent entities on demand (lazy) means when we call the dependent table then it will load.



### <u>Fetch Type</u>

- When you define the mapping relationship 

- You can specify the fetch type: **EAGER** or **LAZY**

  ```java
  @Entity
  @Table(name="instructor")
  public class Instructor {
      ...
      @OneToMany(fetch=FetchType.LAZY, mappedBy="instructor")
      private List<Course> courses;
      
      ...
  }
  ```

  **Default Fetch Type**

  |   Mapping   | Default Fetch Type |
  | :---------: | :----------------: |
  |  @OneToOne  |  FetchType.EAGER   |
  | @OneToMany  |   FetchType.LAZY   |
  | @ManyToOne  |  FetchType.EAGER   |
  | @ManyToMany |   FetchType.LAZY   |



### <u>More about Lazy Loading</u>

- **To retrieve lazy data, we need to open Hibernate Session**

- Retrieve lazy data using
  - **Option 1:** **session.get()** and call appropiate getter method(s)
  - **Option 2:** Hibernate query with HQL
  
- Many Other techniques available but the two above are most common

  

  > **Option 1:  session.get() and call appropiate getter method(s)**

  ```java
  try {
  	session.beginTransaction();
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
  ```

  > **Option 2: Hibernate query with HQL** 

  ```java
  try {
  	session.beginTransaction();
  	// option 2: Hibernate query with HQL	
  	// create the instructor from db 
      int theId = 2;
      // as we are using "JOIN FETCH i.courses" in the HQL query it will also load the course table 
      // so if we want to get access the course table after sesson.close() it will work
      Query<Instructor> query = session.createQuery("select i from Instructor i " +
                                                    "JOIN FETCH i.courses "+
                                                    "where i.id =:theInstructorId", Instructor.class);
  	query.setParameter("theInstructorId", theId);
  	// execute query and get instructor
  		
      Instructor tempInstructor = query.getSingleResult();
      System.out.println("Temp Instructor : " + tempInstructor);
      session.getTransaction().commit();
      System.out.println("DONE!!!!");
  	}
  finally {
     	session.close(); // clean up the code
      factory.close();
  }		
  ```

  