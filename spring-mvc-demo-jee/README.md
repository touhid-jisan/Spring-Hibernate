## Step 1 : Configure Spring MVC

- We need Spring MVC Framework and its dependencies. - **(Add requires jars to the project.)**

- Spring MVC uses Front Controller Pattern -> Dispatcher Servlet. - (Add Dispatcher Servlet to web.xml)

- DispatcherServlet needs an Spring Application Context to launch. Will create an xml (/WEB-INF/todo-servlet.xml). - **(Add Spring Context)**

  ### Useful Snippets 

  > **pom.xml** - spring mvc dependency
  >
  > ```xml
  > <dependency>
  > 	<groupId>org.springframework</groupId>
  >     <artifactId>spring-webmvc</artifactId>
  >     <version>4.2.2.RELEASE</version>
  > </dependency>
  > ```

  ****

  > **web.xml**
  >
  > ```xml
  > <servlet>
  >     <servlet-name>dispatcher</servlet-name>
  > 	<servlet-class>
  >         org.springframework.web.servlet.DispatcherServlet
  >     </servlet-class>
  >     <init-param>
  >         <param-name>contextConfigLocation</param-name>
  >         <param-value>/WEB-INF/todo-servlet.xml</param-value>
  >     </init-param>
  >     <load-on-startup>1</load-on-startup>
  > </servlet>
  > 	
  > <servlet-mapping>
  >     <servlet-name>dispatcher</servlet-name>
  >     <url-pattern>/spring-mvc/*</url-pattern>
  > </servlet-mapping>
  > ```

  ------

  

  > **todo-servlet.xml**
  >
  > ```xml
  > <beans xmlns="http://www.springframework.org/schema/beans"
  > 	    xmlns:context="http://www.springframework.org/schema/context"
  > 	    xmlns:mvc="http://www.springframework.org/schema/mvc"
  > 	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  > 	    xsi:schemaLocation="http://www.springframework.org/schema/beans 		http://www.springframework.org/schema/beans/spring-beans.xsd
  > 	    http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
  > 	    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
  > 	
  > 	    <context:component-scan base-package="com.touhid.jee" />
  > 	    <mvc:annotation-driven />	    
  > </beans>
  > ```

  

## Step 2: Spring MVC Controller

- @RequestMapping(value = "/login", method = RequestMethod.GET)
- http://localhost:8080/spring-mvc/login
- web.xml - /spring-mvc/*
- Why @ResponseBody? Ans: if we want to return some body like string then use Responsbody. if we want to return view then use @RequestMapping(value = "/login", method = RequestMethod.GET)
- Important of RequestMapping method
- Can I have multiple urls rendered from Same Controller?



> **LoginController.java**
>
> ```JAVA
> @Controller
> public class LoginController {	
> 	@RequestMapping(value="/login")
> 	@ResponseBody
> 	public String sayHello(){
> 		return "Hello";
> 	}
> }
> ```



​		

## Step 3: Redirect to Login JSP

- View Resolver in todo-servlet.xml
- Update LoginController
- Remove @ResponseBody
- More about View Resolver



> **View Resolver in todo-servlet.xml**
>
> ```java
> <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
> 	<property name="prefix">
> 		<value>/WEB-INF/views/</value>
> 	</property>
> 	<property name="suffix">
> 		<value>.jsp</value>
> 	</property>
> </bean>
> ```

> **Update LoginController** and **Remove @ResponseBody**
>
> ```java
> @Controller
> public class LoginController {
> 	
> 	@RequestMapping(value="/login", method=RequestMethod.GET )
> 	public String sayHello(){
> 		return "login";
> 	}
> }
> ```



> ### Get and Response Flow
>
> When user enters http://localhost:8080/spring-mvc/login  we are **requesting** to get the login page right? and any request goes to **DispatcherServlet / Front Controller**.  so **DispatcherServlet** sees subsequence url which is "\login".    **DispatcherServlet** will tell  go to **/WEB-INF/todo-servlet.xml** and Scan for all components based on package. 
>
> Then Spring MVC will scan All the component. by **@Controller** and search for mapping by **@RequestMapping**. if value matches by "login" it will return the jsp file name if we use **method="RequestMethod.GET"** . Here comes **View Resolver**. View Resolver checks **todo-servlet.xml** and takes the "login" and give the **"/WEB-INF/views/login.jsp"** by using **prefix** and **suffix** which is the output.
>
> Now **DispatcherServlet** redirect the **response** the contents of **login.jsp** to the browser.



#### Spring MVC Architecture: 

#### 										![](https://www.tutorialspoint.com/spring/images/spring_dispatcherservlet.png)  

































http://localhost:8080/spring-mvc/login



spring-mvc

DispatcherServlet -> Front Controller



/login -> LoginController (Hadler)

if Responsebody then "hello"

if use @RequestMapping(value="\login", method=requestMethod.GET) then it will search for login.jsp link in the bean



View Resolver: 

loging-> /WEB-INF/views/login.jsp



login.jsp -> view