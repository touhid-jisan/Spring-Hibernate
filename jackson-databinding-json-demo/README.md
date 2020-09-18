#  REST APIs - REST Web Services  



**Annotations:** 

1. **@JsonIngnoreProperties(ignoreUnknown=true)**
2. **@RestController** - (extension of @Controller)



**REST** - <u>RE</u>presentational <u>S</u>tate <u>T</u>ransfer.



#### **What is JSON:**

- JavaScript Object Notation

- Lightweight data format for storing and exchanging data. plain text

- Language independent ... not just for **JavaScript**

- Can use any programming language

  



#### **Simple JSON Example**

> - Curly braces define objects in JSON
>
> - Object members are name / value pairs 
>
>   - Delimited by colons
>
> - Name is **always** in double
>
>   ![](https://githubpictures.000webhostapp.com/pictures/JSON.png)
>
> ****





#### **JSON Values:**

- **Numbers:** no quotes

- **String:** in double quotes

- **Boolean:** true, false

- **Nested** JSON object

- **Array** 

- **null**

  **Nested JSon Object:**

  ![](https://githubpictures.000webhostapp.com/pictures/json-nested.png)

  **JSON Array**

  ```json
  {
  	"id": 14,
      "firstName": "Maria",
      "lastName": "Rossi",
      "active": true,
      "laguages": ["Java", "C#","Pyhon", "JavaScript"]
  }
  ```

  



#### **Java JSON Data Binding:**

- Data binding is the process of converting JSON data to a Java POJO

  ![](https://githubpictures.000webhostapp.com/pictures/json-to-java.png)



#### **JSON data Binding with Jackson:**

- Spring uses the **Jackson Project** behind the scenes.
- **Jackson** handles data binding between JSON and Java POJO



#### **Jackson Data Binding:**

- By default, Jackson will call appropriate getter setter methods

- Jackson Data Binding API

  - Package: **com.fasterxml.jackson.databind**

- Maven Dependency

  ```xml
  <dependency>
  	<groupId>com.fasterxml.jackson.core</groupId>
  	<artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
  </dependency>
  ```

  

  

#### **JSON to Java POJO:**

- Convert JSON to POJO... call setter methods on POJO

- **Note:** Jackson Calls the setter methods. It does not access internal private fields directly 

  ![](https://githubpictures.000webhostapp.com/pictures/convert-json-to-java-1.png)

  

  ![](https://githubpictures.000webhostapp.com/pictures/convert-json-to-java-2.png)

  

- ​    ![](https://githubpictures.000webhostapp.com/pictures/convert-json-to-java-3.png)







#### **Java POJO to JSON:**

- Convert Java POJO to JSON... call getter methods on POJO

  ![](https://githubpictures.000webhostapp.com/pictures/pojo-to-json.png)

  

  ![](https://githubpictures.000webhostapp.com/pictures/java-to-json-2.png)





#### **Spring and Jackson Support:**

- When building spring REST application Spring will automatically handle **Jackson Integration**
- JSON data being passed to REST controller is converted to POJO
- Java object being returned from REST controller is converted to POJO 





#### **Development Process:**

- **Step 1:** download and import maven starter files

- **Step 2:** Add maven dependency for Jackson projcect

- **Step 3:** Create Student POJO Java Class

- **Step 4:** Create main Driver App to read data 

  

  **Step 2: Add maven dependency for Jackson projcect**

  > pom.xml
  >
  > ```xml
  > <dependency>
  > 	<groupId>org.springframework</groupId>
  > 	<artifactId>spring-webmvc</artifactId>
  >     <version>5.0.5.RELEASE</version>
  > </dependency>
  > ```
  >
  > ****

  

  **Step 3: Create Student POJO Java Class**

  > Student.java
  >
  > ```java
  > public class Student {
  > 
  > 	private int id;
  > 	private String firstName;
  > 	private String lastName;
  > 	private boolean active;
  > 
  > 	public Student() {
  > 
  > 	}
  > 
  > 	public int getId() {
  > 		return id;
  > 	}
  > 
  > 	public void setId(int id) {
  > 		this.id = id;
  > 	}
  > 
  > 	public String getFirstName() {
  > 		return firstName;
  > 	}
  > 
  > 	public void setFirstName(String firstName) {
  > 		this.firstName = firstName;
  > 	}
  > 
  > 	public String getLastName() {
  > 		return lastName;
  > 	}
  > 
  > 	public void setLastName(String lastName) {
  > 		this.lastName = lastName;
  > 	}
  > 
  > 	public boolean isActive() {
  > 		return active;
  > 	}
  > 
  > 	public void setActive(boolean active) {
  > 		this.active = active;
  > 	}
  > 
  > }
  > ```
  >
  > ****

  

  **Step 4: Create main Driver App to read data**

  > Driver.java ( main class )
  >
  > <script src="https://gist.github.com/touhid-jisan/c547564dc96a88d3aa0493e98cfb5a4f.js"></script>
  >
  > ****

  ====> > > to ignore property from JSON add annotation **"@JsonIgnoreProperties(ignoreUnknoew=true)"**

  

  



#### REST HTTP Basics:

- Most common use of REST is over HTTP

- Leverage HTTP methods for CRUD Operations

  | HTTP Method | CRUD Operation                           |
  | ----------- | ---------------------------------------- |
  | POST        | Create a new entity                      |
  | GET         | Read a list of entities or single entity |
  | PUT         | Update an existing entity                |
  | DELETE      | Delete an existing entity                |



#### HTTP Messages:

![](https://githubpictures.000webhostapp.com/pictures/http-messages.png)

#### HTTP Request Message:

- Request line: the HTTP command
- Header variables: request metadata
- Message body: contents of message

#### HTTP Response Messages:

- Response line: server protocol and status code (like 400, 404 etc.)
- Header variable: response variable (information about data. for example: xml/json etc.)
- Message body: contents of body

#### HTTP Response: Status Code

| Code Range | Description                                                  |
| ---------- | ------------------------------------------------------------ |
| 100-199    | Informational                                                |
| 200-299    | Successful                                                   |
| 300-399    | Redirection                                                  |
| 400-499    | Clint error ( 401 - authentication error<br />404- File not found) |
| 500-599    | Server error (500 - internal server error)                   |



#### Mime (Multipurpose Internet Mail-Extension) Content Types:

- The message format is described by MIME content type
- Basic Syntax: type/sub-type
- Examples:
  -  text/html, text/plain
  - application/json, application/xml

#### Clint Tool:

- Send HTTP request to the REST Web Service / API
- Plenty of tools : curl, **Postman** etc.







#### Spring Rest Controller:

- New annotation **@RestController**
  - Extension of @Controller
  - Handles REST request and responses
- Spring REST will also automatically convert Java to POJOs to JSON
  - As long as the Jackson Project is on the class path or pom.xml

<script src="https://gist.github.com/touhid-jisan/94a18f509b20cebd245677c85485946c.js"></script>



#### Spring REST Controller Development Process:

- **Step 1:** Add Maven dependency for Spring MVC and Jackson project - [pom.xml](https://gist.github.com/touhid-jisan/282bdbf9b731c4bb3baf5ca6b2e41c4f#file-pom-xml)
- **Step 2:** Add code for all Java Config: @Configuration - [DemoAppConfig.java](https://gist.github.com/touhid-jisan/282bdbf9b731c4bb3baf5ca6b2e41c4f#file-demoappconfig-java) 
- **Step 3:** Add code for all Java Config: Servlet Initializer - [MySpringMvcDispatcherServletInitializer.java](https://gist.github.com/touhid-jisan/282bdbf9b731c4bb3baf5ca6b2e41c4f#file-myspringmvcdispatcherservletinitializer-java)
- **Step 4:** Create Spring REST Service uising **@RestController** - [DemoRestController.java](https://gist.github.com/touhid-jisan/282bdbf9b731c4bb3baf5ca6b2e41c4f#file-demorestcontroller-java)

<script src="https://gist.github.com/touhid-jisan/282bdbf9b731c4bb3baf5ca6b2e41c4f.js"></script>



