# Hibernate Many-to-Many Relationship

- A  course can have many students.

- a student can have many courses

- New to track which student is in which course, vice-versa  by using **JoinTable** 

  ## Join Table

  *A table that provides a mapping between two tables. It has foreign keys for each table to define the mapping relationship.*

  <img src="https://githubpictures.000webhostapp.com/pictures/jointable-many2many.png" style="zoom:50%;" />

​							<u>This Join Table(**course_student**) maintains the relationship between **courses** and **student**</u>   



​							<img src="https://githubpictures.000webhostapp.com/pictures/jointableexample.png" style="zoom:50%;" />

​							  

## Development Process: Many to Many

1. Prep Work - Define database tables
2. Update **Course** class
3. Update **Student** class
4. Create Main App

### Step 1: 

```
file: create-db.sql	
```

```sql
CREATE TABLE course_student (
	course_id int(11) NOT NULL,
    student_id int(11) NOT  NULL,
    
    PRIMARY KEY(course_id, student_id),
    
    CONSTRAINT FK_COURSE_05
    FOREIGN KEY (course_id)
    REFERENCE course(id),
    
    CONSTRAINT FK_STUDENT
    FOREIGN KEY (student_id)
    REFERENCE student(id)
   
);
```



### Step 2: Update Course - reference students

```
file: course.java
```

```java
@Entity
@Table(name="course")
public class Course {
    
// other fields    
    
    @ManyToMany
    @JoinTable(
    	name="course_student",
        joinColumns=@JoinColumn(name="course_id"),
        inverseJoinColumns=@JoinColumn(name="student_id")
    	)
    private List<Student> students;
}
// getters, setter, constructors

// add convenience student method
public void addStudent(Student theStudent) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(theStudent);
	}
```

#### <u>More: @JoinTable</u>

- **@JoinTable** tells hibernate 
  - Look at the **course_id** column in the course_student table
  - For the side (inverse), look at the **student_id** column in the **course_student** table
  - Use this information to find relationship between **course** and **students**

#### <u>More on "inverse"</u>

​						![](https://githubpictures.000webhostapp.com/pictures/inverse_relationship.png)

​	

- We are defining the relationship in the **Course** class.
- The **Student** class is on the other side, so it is considered the "inverse"
- "Inverse" refers to the "other side" of the relationship. 



### Step 3: Update Student - reference courses

```
file: student.java
```

```java
@Entity
@Table(name="course")
public class Student {
	
    @ManyToMany
    @JoinTable(
    	name="course_student",
        joinColumns=@JoinColumn(name="student_id"),
        inverseJoinColumns=@JoinColumn(name="course_id")
    	)
	private List<Course> courses;
}
```

#### <u>More: @JoinTable</u>

- **@JoinTable** tells hibernate 
  - Look at the **student_id** column in the **course_student** table
  - For the side (inverse), look at the **course_id** column in the **course_student** table
  - Use this information to find relationship between **students** and **courses**

#### <u>More on "inverse"</u>

- We are defining the relationship in the **Student** class
- The **Course** class is on the "other side", so it is considered the "inverse"
- "Inverse" referes to the "other side" of the relationship

![](https://githubpictures.000webhostapp.com/pictures/inverse_relationship2.png)



## Step 4: Create Main App



> #### 1. Create Courses With Students

```
file: CreateCourseAndStudentDemo.java
```

```java
try {
	session.beginTransaction();
			
	// create course
	Course tempCourse = new Course("CSE-110");
	session.save(tempCourse);
			
	// create 2 student obj	
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
```



> #### **2. Add more courses to a specific students** 

```
file: AddCoursesForTouhidDemo.java
```

```java
try {
	session.beginTransaction();
			
    // 1. get the student touhid from database
    // 2. create more courses
    // 3. add student to courses
    // 4. save the courses 
			
	int studentId = 1;
    Student tempStudent = session.get(Student.class, studentId);
			
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
```



> #### **3. Get Courses for Specific Students** 

```
file: GetCoursesForTouhidDemo.java
```

```java
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
```



> #### 4. Delete Student Without Deleting Any Course

```
file: DeleteStudentWithoutDeletingCourse.java
```

```java
try {
	session.beginTransaction();
			
	int studentId = 1;
	Student tempStudent= session.get(Student.class, studentId);
	session.delete(tempStudent);
			
	session.getTransaction().commit();
	System.out.println("DONE!!!!");
}finally {
	session.close(); // clean up the code
	factory.close();
}
```



> #### 5. Delete Course Without Deleting Student

```
file: DeleteCourseWithoutDeletingStudent.java
```

```java
try {
	session.beginTransaction();
			
	int courseId = 10;
    Course tempcourse= session.get(Course.class, courseId);
    session.delete(tempcourse);
		
    session.getTransaction().commit();
	System.out.println("DONE!!!!");
}finally {
	session.close(); // clean up the code
	factory.close();
}
```

