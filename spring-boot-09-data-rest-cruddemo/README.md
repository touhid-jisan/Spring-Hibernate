# Spring Data Rest





- We saw the magic of Spring Data JPA

- This helped to eliminate boilerplate code

  ![](https://githubpictures.000webhostapp.com/pictures/jpa-methods.png)

  ![](https://githubpictures.000webhostapp.com/pictures/after-spring-data-jpa.png)



**The problem:**

- We know how to create a REST API for Employee
- What will we do if we need to create REST API for another entity?
  - Customer, Student, Product, Book... etc
- Do we have to repeat all the code again?



**Solution:**

### Spring Data Rest

- Leverages existing **JpaRepository**
- Spring will give us a REST CRUD implementation for FREE like magic
  - Helps to minimize boiler-plate REST code.
  - No new Code requires



#### Rest Api

- **Spring Data REST** will expose these endpoints for free

  | HTTP Method |                         | CRUD Action                 |
  | ----------- | ----------------------- | --------------------------- |
  | POST        | /employees              | Create a new employee       |
  | GET         | /employees              | Read a list of employees    |
  | GET         | /employees/{employeeId} | Read a single employee      |
  | PUT         | /employees/{employeeId} | Update an existing employee |
  | DELETE      | /employees/{employeeId} | Delete an existing employee |



#### Spring Data REST - How Does It Work?

- Spring Data REST will scan the project for JpaRepository

- Expose REST APIs for each entity type for JpaRepository

  ```java
  public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {
  
  }
  ```

  

#### Rest Endpoint

- By default, Spring Data REST will create endpoints based on entity type

- Simplye pluralized form

  - First character of Entity type is lowecase 

  - Then just add a "s" to the entity

    ![](https://githubpictures.000webhostapp.com/pictures/spring-data-rest-pluralized.png)

    Spring Data REST does not handle complex pluralized form

    In this case, we need to specify plural form	

    ```java
    @RepositoryRestResource(path="members")
    public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    }
    ```

    ```
    http://localhost:8080/members
    ```

  - JpaRepository<Employee , Integer> ===> employees

  

### Advanced Features

- Spring Data REST advanced features
  - Pagination, sorting and searching



#### Paginationa

- By default, Spring Data REST will return the first 2-=0 elements

  - Page size = 20

- We can navigate to the different pages of data using query param

  ![](https://githubpictures.000webhostapp.com/pictures/pagination.png)



**Spring Data REST Configuration**

- following properties available: **application.properties**

| Name                                                         | Description                                   |
| ------------------------------------------------------------ | --------------------------------------------- |
| spring.data.rest.base-path                                   | Base path used to expose repository resources |
| spring.data.rest.default-page-size                           | Default size of pages                         |
| spring.data.rest.max-page size                               | Maximum size of pages                         |
| [more props](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties) |                                               |

#### Sorting

- We can sort by the property names of the entity

  - In our Employee example, we have **firstName**, **lastName**, **email**

  - Sort by last name(ascending is default)

    ```
    http://localhost:8080/employees?sort=lastName
    ```

  - Sort by first name, descending

    ```
    http://localhost:8080/employees?sort=firstName, desc
    ```

  - Sort by last name, first name, ascending

    ```
    http://localhost:8080/employees?sort=lastName,firstName,asc
    ```

    



#### Development Process

- **Step 1:** Add Spring Data REST to the pom file

  That's it. Absolutely NO CODING required

  ```xml
  <dependency>
  	<groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-startar-data-rest</artifactId>
  </dependency>
  ```

  Spring Data REST will scan for JpaRepository

 

#### In A Nutshell

For Spring Data REST, we only need 3  times

1. Entity: **Employee**

2. JpaRepositorty: **EmployeeRepository extends JpaRepository**

3. Maven POM dependency for: **spring-boot-startar-data-rest**

   ![](https://githubpictures.000webhostapp.com/pictures/spring-data-rest1.png)



### Minimized Boilerplate Code

![](https://githubpictures.000webhostapp.com/pictures/befor-after-spring-data-rest.png)





### HATEOAS

- Spring Data REST endpoints are **HAETEOAS** compliant

- **HATEOAS:** <u>**H**</u>ypermedia <u>**a**</u>s <u>**t**</u>he <u>**E**</u>ngine <u>**o**</u>f <u>**A**</u>pplication <u>**S**</u>tate

- Hypermedia-driven sites provide information to access REST interface

  - Think of it as meta-data for REST data
  - [See More](https://spring.io/guides/gs/rest-hateoas/)

- Spring Data REST response using HATEOAS

  - For example REST response from : **GET/employee/3**

    ![](https://githubpictures.000webhostapp.com/pictures/HATEOAS.png)

  

- For a collection, meta-data includes page size, total elements, pages etc

  - 

    ![](https://githubpictures.000webhostapp.com/pictures/hateoas-response.png)

  

- HATEOAS uses **HAL** - **<u>H</u>**ypertext <u>**A**</u>pplication **<u>L</u>**anguage data format





### Advanced Features

- Spring Data REST advanced features
  - Pagination, sorting and searching
  - Extending and adding custom queries withh JPQL
  - Query Domain Specific Language (Query DSL)       