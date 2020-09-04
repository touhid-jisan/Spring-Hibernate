# Spring MVC Step By Step



### Project Setup: 

- create a maven project

  - any maven project needs to have a **name** , a name has two part 

    - **Group Id(package name)**
    -  **Artifact Id**

  - packaging :

    -  war - web archive (for web application)
    - jar - java archive

    ###### <u>(this information will be in pom.xml file)</u>

    **pom.xml is used by Maven. To run a web application we need lot of jar files. i can ask maven to download these jar file is called dependency management**



### 1. Mavin and Magic



pom.xml :

> ##### ** The most important in Mavin we can add plugins, Forexample** 

1. ##### **First Plugin: We are able to run the web application in tomcat by**

   ```xml
   <plugin>
   	<groupId>org.apache.tomcat.maven</groupId>
   	<artifactId>tomcat7-maven-plugin</artifactId>
   	<version>2.2</version>
   	<configuration>
   		<path>/</path>
   		<contextReloadable>true</contextReloadable>
   	</configuration>
   </plugin>
   ```

   

2. **Second Plugin: To be able to compile the code using java 1.8**

   ```xml
   <plugin>
   	<groupId>org.apache.maven.plugins</groupId>
   	<artifactId>maven-compiler-plugin</artifactId>
   	<version>3.2</version>
   	<configuration>
   		<verbose>true</verbose>
   		<source>1.8</source>
   		<target>1.8</target>
   		<showWarnings>true</showWarnings>
   	</configuration>
   </plugin>
   ```

   

3. **We add dependency**

   ```xml
   <dependency>
   	<groupId>javax</groupId>
   	<artifactId>javaee-web-api</artifactId>
   	<version>6.0</version>
   	<scope>provided</scope>
   </dependency>
   ```

   

   web.xml

   > It is an application configuration file of Java Web Development Environment. It provides context information to JSP pages (Java Server Pages). It is used to set application variables and connection string to the database and many more.

   



### 2. What is Servlet?

> Ans: **Servlet** is a java programming language class used to extend capabilities of servers that host applications accessed by means of a **request-response** programming model
>
> in Short a **Servlet** is nothing buy a Java class which can take request in and **response** as the output

1. A servlet should extend **HttpServlet**
2. Q: how do we come to this sevelet? Ans: Using this urlPaterns
   - **@WebSevlet(urlPaterns="/login.do")** 
3. In servelt we need to define what kind of method we want to handle. For example, **doGet()** or **doPost()**
   - We create a **doGet()** method to handle the **request** and we send a **response**. **request** is the input **response** is the output.

> LoginServlet.java

```java
@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        				throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
}
```

 



- #### Setting default home page 

  > **in web.xml**
  >
  > ```xml
  > <welcome-file-list>
  > 	<welcome-file>login.do</welcome-file>
  > </welcome-file-list>
  > ```

- #### Forwarding to jsp 

  > **in servlet**
  >
  > ```java
  > request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
  > ```

- #### Getting value in Servlet from Browser

  > **passing value from browser to servlet**
  >
  > **in servlet:**
  >
  > ```java
  > request.getParameter("name") // geting value from browser to servlet
  > ```

- #### Setting value in Servlet for Browser

  > **in servlet:** 
  >
  > ```java
  > String name = "jisan";
  > request.setAttribute("name" , name); // passing value from sevlet to jsp
  > ```

- #### Getting value in JSP from Servlet

  > **in .jsp**
  >
  > ```jsp
  > <body>
  >     <h1>
  >         Name : ${name} <!-- expression language -->
  >     </h1>
  > </body>
  > ```

- #### Scriplet

  > in jsp
  >
  > ```jsp
  > <%@page import="java.util.Date"%>
  > <%
  > Date date = new Date();
  > %>
  > <div>
  >     Current Date is : <%=date%>
  > </div>
  > ```

  **But remember jsp is view. we should not use any business logic in jsp. we will only use expression language in jsp**

  **Scriplet is very very bad practice.**

  

- #### Passing parameter manually from browser link tab  

  > starts with **?** and between param name use **&**
  >
  > ```apl
  > localhost:8080/?username=touhid&password=touhid
  > ```

  **Use post method for passing data. For making data secure**



- #### doPost() and doGet() method example: 

  ```java
  @WebServlet(urlPatterns = "/login.do")
  public class LoginServlet extends HttpServlet{
  	
  	private UserValidationService service = new UserValidationService();
  	
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
  	}
  	
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  		String username = request.getParameter("user_name");
  		String password = request.getParameter("user_password");
  		
  		boolean isUserValid = service.isValid(username, password);
  		if(isUserValid) {
  			request.setAttribute("username", username); 
  			request.setAttribute("userpass", password);
  			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
  		}
  		else {	
  			request.setAttribute("errorMessage", "Invalid Username and password");
  			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);    
          }
  	}
  }
  ```





