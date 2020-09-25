# Thymeleaf with Spring Boot





### What is Thymeleaf

- Thymeleaf is a Java templating engine
- Commonly used to generate the HTML views for web apps
- However, it is a general purpose templating engine
  - Can use Thymeleaf outside of web apps 
- We cn create Java apps with Thtmeleaf - No need for Spring



### What is Thymeleaf Template?

- Can be a HTML page with some Thymeleaf expression
- Include dynamic content from Thymeleaf expression
- Can access Java code, objects, Spring beans



### Where is the Thymeleaf template processed?

- In a web app, Thymeleaf is processed on the server

- Results included in HTML returned to browser

  ![](https://githubpictures.000webhostapp.com/pictures/thymeleaf-processed.png)





**THIS IS SIMILAR TO JSP**

### Thymeleaf vs JSP

- One key difference
  - JSP can only be used in  a web environment
  - Thymeleaf can be used in web **OR** non-web environments



### Thymeleaf Use Cases (non-web)

1. Email Template
   - When students signs up for a course then send welcome email
2. CSV Template
   - Generate a monthly report as CSV then upload to Google drive
3. PDF Template
   - Generate a travel confirmation PDF then send via email





### Development Process - Simple Demo

- **Step 1:** Add Thymeleaf to Maven POM file

- **Step 2** Develop Spring MVC Controller

- **Step 3:** Create Thymeleaf template

  

  **Step 1: Add Thymeleaf to Maven POM file**

  ```xml
  <dependency>
  	<groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-startar-thymeleaf</artifactId>
  </dependency>
  ```

  **Step 2: Develop Spring MVC Controller**

  > DemoController.java
  >
  > ```java
  > @Controller
  > public class DemoController {
  > 	
  >     @GetMapping("/")
  >     public String sayHello(Model theModel) {
  >         theModel.setAttribute("theDate" , new java.util.date());
  >         return "helloworld";
  >     }
  > }
  > ```

  **Step 3: Create Thymeleaf template**

  > src/main/resources/template/helloworld.html
  >
  > ```html
  > <!DOCTYPE HTML>
  > <html xmlns:th="http://thymeleaf.org">
  >     
  >     <head>    </head>
  >     
  >     <body>
  >         <p th:text="'Time on the server is' + ${theDate}"
  >     </body>
  > 
  > </html>
  > ```
  >
  > ****



### Where to place Thymelead template?

- In Spring Boot, Thymeleaf template go in

  > src/main/resources/template

- For web apps, Thymeleaf template have **.html** extension





### Additional Features

- Looping and conditionals
- CSS and JavaScript integration
- Template layouts and fragments







# CSS and Thymeleaf



### Using CSS with Thymeleaf Teamplates

- We have the option of using
  - Local CSS files as part of the project
  - Refactoring remote CSS files





### Development Process

- **Step 1:** Create CSS file

- **Step 2:** Reference CSS in Thymeleaf template

- **Step 3:** Apply CSS style

  

  **Step 1: Create CSS file**

  - Spring Boot will look for static resources in the directory

    > src/main/resources/static

    ![](https://githubpictures.000webhostapp.com/pictures/thymeleaf-css.png)

  - We can create our own custom sub-directories

    1. static/css or
    2. static/images or
    3. static/js .. etc

  

  **Step 2: Reference CSS in Thymeleaf template**

  > file: helloworld.html
  >
  > ```html
  > <head>
  > 	<title>Thymeleaf Demo</title>
  >     <!-- reference css file -->
  >     <link rel="stylesheet" th:href="@{/css/demo.css}" />
  >     
  > </head>
  > ```
  >
  > @ symbole reference context path of application (app root)

  

  **Step 3: Apply CSS style**

  > file: helloworld.html
  >
  > ```html
  > <body>
  > 
  > 	<p th:text="Time on the server is + ${theDate}" class="funny"  />
  >     </p>
  >     
  > </body>
  > ```
  >
  > ****

  

  

### Other Search Directories

Spring Boot will search following directories for static resources:

**/src/main/resources** (under this directory spring boot will serach for)

- search order **top-down**

> 1. **/META-INF/resources**
> 2. **/resources**
> 3. **/static**
> 4. **/public**





### 3rd Party CSS Libraries - Bootstrap Local Installation

- Download Bootstrap files and add to **/static/css** directory

  ![](https://githubpictures.000webhostapp.com/pictures/bootstrap-loca.png)

  ```html
  <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}" />
  ```

  



### 3rd Party CSS Libraries - Bootstrap Remote Files

```html
<link rel="stylesheet" th:href="@{/css/bootstrap.min.css}" />
```

