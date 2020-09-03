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

