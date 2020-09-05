## Step 1 : Configure Spring MVC

- We need Spring MVC Framework and its dependencies. - **(Add requires jars to the project.)**

- Spring MVC uses Front Controller Pattern -> Dispatcher Servlet. - **(Add Dispatcher Servlet to web.xml)**

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



### Spring MVC Request Flow

- DispatcherServlet receives HTTP Request.
- DispatcherServlet identifies the right Controller based on the URL.
- Controller executes Business Logic.
- Controller returns a) Model b) View Name Back to DispatcherServlet.
- DispatcherServlet identifies the correct view (ViewResolver).
- DispatcherServlet makes the model available to view and executes it.
- DispatcherServlet returns HTTP Response Back.
- Flow : http://docs.spring.io/spring-framework/docs/2.0.8/reference/images/mvc.png

#### Spring MVC Architecture: 

#### 										![](https://www.tutorialspoint.com/spring/images/spring_dispatcherservlet.png)  







## Step 4: Add Logging Framework Log4j to understand the flow much more.



> **/pom.xml**
>
> ```xml
> <!-- https://mvnrepository.com/artifact/log4j/log4j -->
> <dependency>
>     <groupId>log4j</groupId>
>     <artifactId>log4j</artifactId>
>     <version>1.2.17</version>
> </dependency>
> 
> ```



> **/src/main/resources/log4j.properties**
>
> ```properties
> log4j.rootLogger=TRACE, Appender1, Appender2
>  
> log4j.appender.Appender1=org.apache.log4j.ConsoleAppender
> log4j.appender.Appender1.layout=org.apache.log4j.PatternLayout
> log4j.appender.Appender1.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n
>  
> #TRACE
> #DEBUG
> #INFO
> #WARN
> #ERROR
> ```



### log4j Overview:

------

log4j is highly configurable through external configuration files at runtime. It views the logging process in terms of levels of priorities and offers mechanisms to direct logging information to a great variety of destinations, such as a database, file, console, UNIX Syslog, etc.

log4j has three main components:

- **loggers**: Responsible for capturing logging information.
- **appenders**: Responsible for publishing logging information to various preferred destinations.
- **layouts**: Responsible for formatting logging information in different styles.

### Pros and Cons of Logging

Logging is an important component of the software development. A well-written logging code offers quick debugging, easy maintenance, and structured storage of an application's runtime information.

Logging does have its drawbacks also. It can slow down an application. If too verbose, it can cause scrolling blindness. To alleviate these concerns, log4j is designed to be reliable, fast and extensible.





## Step 5: Important Tasks

- Show userid and password on the welcome page.
- We will not use Spring Security for now.
- ModelMap model
- @RequestParam String name

 

> 1. Added **method=RequestMethod.POST** 
> 2. Added **ModelMap**. **ModelMap** is an extension of Model with the ability to store attributes in a map and chain method calls.
> 3. Used  **@RequestParam** to get the value from jsp form input
>
> ```java
> @Controller
> public class LoginController {
> 	
> 	@RequestMapping(value="/login", method=RequestMethod.GET )
> 	public String showLoginPage(){
> 		return "login";
> 	}
> 	
> 	@RequestMapping(value="/login", method=RequestMethod.POST )
> 	public String showWelcomePage(ModelMap model, @RequestParam String user_name, @RequestParam String user_password){
> 		model.put("username", user_name);
> 		model.put("password", user_password);
> 		return "welcome";
> 	}
> }
> ```



**Added log4j Dependency**

> **/pom.xml**
>
> ```xml
> <dependency>
> 	<groupId>log4j</groupId>
> 	<artifactId>log4j</artifactId>
> 	<version>1.2.17</version>
> </dependency>
> ```



> **/src/main/resources/log4j.properties**
>
> ```properties
> log4j.rootLogger=TRACE, Appender1, Appender2
>  
> log4j.appender.Appender1=org.apache.log4j.ConsoleAppender
> log4j.appender.Appender1.layout=org.apache.log4j.PatternLayout
> log4j.appender.Appender1.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n
> 
> #TRACE
> #DEBUG
> #INFO
> #WARN
> #ERROR
> #FATAL
> #ALL
> ```



##### Debug Level

We have used DEBUG with both the appenders. All the possible options are:

- TRACE
- DEBUB
- INFO
- WARN
- ERROR
- FATAL
- ALL



## Step 6:

- Use **LoginValidationService** to validate username and password.
- Remove all the old controller code and lets use only **Spring MVC** here on.
- For now : We are not using Spring **Autowiring** for LoginService.
- Change URL to http://localhost:8080/login



> **/LoginController.java**
>
> ```java
> @Controller
> public class LoginController {
> 	
> 	LoginValidationService validService = new LoginValidationService();
> 	
> 	@RequestMapping(value="/login", method=RequestMethod.GET )
> 	public String showLoginPage(){
> 		return "login";
> 	}
> 	
> 	@RequestMapping(value="/login", method=RequestMethod.POST )
> 	public String showWelcomePage(ModelMap model, @RequestParam String user_name, @RequestParam String user_password){
> 		if(!validService.isValid(user_name, user_password)) {
> 			model.put("errorMessage", "Wrong Username or password");
> 			return "login";
> 		}
> 		model.put("username", user_name);
> 		model.put("password", user_password);
> 		return "welcome";
> 	}
> }
> ```

------



> **\LoginValidationService.java**
>
> ```java
> public class LoginValidationService {
> 	
> 	public boolean isValid(String username, String password) {
> 		if(username.equals("touhid") && password.equals("jisan")) {
> 			return true;
> 		}
> 		return false;
> 	}
> 	
> }
> ```

------



> **/src/main/webapp/WEB-INF/views/login.jsp**
>
> ```html
> <body>
>     <form action="/spring-mvc/login" method="post">
>         ${errorMessage}
>         <br><br>
>         Enter name <input type="text" name="user_name"/><br><br>
>         Enter password <input type="password" name="user_password"/><br><br>
>         <input type="submit"value="Login"/>
>     </form>
> </body>
> ```

------



> **/src/main/webapp/WEB-INF/views/welcome.jsp**
>
> ```jsp
> <body>
>     Welcome: 
>     ${username} ,  ${password} 
> </body>
> ```
>
> 

## Step 7:

- Learn about Spring Auto-wiring and Dependency Management.
- Use Auto-wiring to wire LoginService.
- @Autowired, @Service



> **LoginController.java**
>
> ```java
> @Controller
> public class LoginController {
> 	
> 	@Autowired
> 	private LoginValidationService validService;
> 	
> 	@RequestMapping(value="/hello", method=RequestMethod.GET )
> 	public String showLoginPage(){
> 		return "login";
> 	}
> 	
> 	@RequestMapping(value="/hello", method=RequestMethod.POST )
> 	public String showWelcomePage(ModelMap model, @RequestParam String user_name, @RequestParam String user_password){
> 		if(!validService.isValid(user_name, user_password)) {
> 			model.put("errorMessage", "Wrong Username or password");
> 			return "login";
> 		}
> 		model.put("username", user_name);
> 		model.put("password", user_password);
> 		return "welcome";
> 	}
> }
> ```



> **LoginValidationService.java**
>
> ```java
> @Service
> public class LoginValidationService {
> 	public boolean isValid(String username, String password) {	
> 		if(username.equals("touhid") && password.equals("jisan")) {
> 			return true;
> 		}
> 		return false;
> 	}	
> }
> ```



## Step 8: New To do list created

> **TodoController.java**
>
> ```java
> @Controller
> public class TodoController {
> 	
> 	@Autowired
> 	TodoService service;
> 	
> 	@RequestMapping(value="/list-todos", method=RequestMethod.GET )
> 	public String showTodoPage(ModelMap model){
> 		model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
> 		
> 		return "list-todos";
> 	}
> }
> ```

> **TodoService.java**
>
> ```java
> @Service
> public class TodoService {
> 	private static List<Todo> todos = new ArrayList<Todo>();
> 	private static int todoCount = 3;
> 	
> 	static {
> 		todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
> 				false));
> 		todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));
> 		todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),
> 				false));
> 	}
> 	
> 	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
> 		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
> 	}
> 	
> 	public List<Todo> retrieveTodos(String user) {
> 		List<Todo> filteredTodos = new ArrayList<Todo>();
> 		for (Todo todo : todos) {
> 			if (todo.getUser().equals(user)) {
> 				filteredTodos.add(todo);
> 			}
> 		}
> 		return filteredTodos;
> 	}
> 	public void deleteTodo(int id) {
> 		Iterator<Todo> iterator = todos.iterator();
> 		while (iterator.hasNext()) {
> 			Todo todo = iterator.next();
> 			if (todo.getId() == id) {
> 				iterator.remove();
> 			}
> 		}
> 	}
> }
> ```

> **list-todos.jsp**
>
> ```jsp
> <body>
> 	${todos}
> </body>
> ```



#### Model 1 Architecture : 

Jsp

No Servlets or Controllers

#### Model 2 Architecture or MVC

- Model - Todo.java
- View - login.jsp 
- Controller - LoginServlet, TodoServlet ....

#### Modification of Model 2 Architecture

Front Controller:  DispatcherServlet ->  All the request will go first to dispatcher servlet and then the dispatcher servlet will decide which one to call



# Spring Modules

![](README.assets/spring-overview.png)

## Step 9: @SessionAttribute

- **Session** vs **Model** vs Request.
- **Be cautious** about what you use Session for.
- **@SessionAttributes("username")** and how it works?
- Why use Model? "adding elements directly to the **HttpServletRequest** (as request attributes) would seem to serve the same purpose. The reason to do this is obvious when taking a look at one of the requirements we have set for the MVC framework: It should be as view-agnostic as possible, which means we’d like to be able to incorporate view technologies not bound to the **HttpServletRequest** as well." - Rod Johnson et. al’s book Professional Java Development with the Spring Framework
- Spring documentation states that the **@SessionAttributes** annotation “list the names of model attributes which should be transparently stored in the session or some conversational storage.”



> **/com/touhid/login/LoginController.java**
>
> ```java
> @Controller
> @SessionAttributes("username")
> public class LoginController {
> 	
> 	@Autowired
> 	private LoginValidationService validService;
> 	
> 	@RequestMapping(value="/", method=RequestMethod.GET )
> 	public String showLoginPage(){
> 		return "login";
> 	}
> 	
> 	@RequestMapping(value="/login-form", method=RequestMethod.POST )
> 	public String showWelcomePage(ModelMap model, @RequestParam String user_name, @RequestParam String user_password){
> 		if(!validService.isValid(user_name, user_password)) {
> 			model.put("errorMessage", "Wrong Username or password");
> 			return "login";
> 		}
> 		
> 		model.put("username", user_name);
> 		model.put("password", user_password);
> 		return "welcome";
> 	}
> }
> ```

------

> **/com/touhid/login/LoginValidationService.java**
>
> ```java
> @Service
> public class LoginValidationService {
> 	public boolean isValid(String username, String password) {	
> 		if(username.equals("touhid") && password.equals("jisan")) {
> 			return true;
> 		}
> 		return false;
> 	}	
> }
> ```

------

> **/com/touhid/todo/Todo.java**
>
> ```java
> package com.touhid.todo;
> 
> import java.util.*;
> 
> public class Todo {
> 	
> 	private int id;
> 	private String user;
> 	private String desc;
> 	private Date targetDate;
> 	private boolean isDone;
>     
> 	// constructor, getters setters, toString(), hashCode()
> 
> }
> ```

> **/com/touhid/todo/TodoController.java**
>
> ```java
> @Controller
> public class TodoController {
> 	
> 	@Autowired
> 	TodoService service;
> 	
> 	@RequestMapping(value="/list-todos", method=RequestMethod.GET )
> 	public String showTodoPage(ModelMap model){
> 		model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
> 		return "list-todos";
> 	}
> }
> ```

> **/com/touhid/todo/TodoService.java**
>
> ```java
> @Service
> public class TodoService {
> 	private static List<Todo> todos = new ArrayList<Todo>();
> 	private static int todoCount = 3;
> 	
> 	static {
> 		todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
> 				false));
> 		todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));
> 		todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),
> 				false));
> 	}
> 	
> 	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
> 		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
> 	}
> 	
> 	public List<Todo> retrieveTodos(String user) {
> 		List<Todo> filteredTodos = new ArrayList<Todo>();
> 		for (Todo todo : todos) {
> 			if (todo.getUser().equals(user)) {
> 				filteredTodos.add(todo);
> 			}
> 		}
> 		return filteredTodos;
> 	}
> 	public void deleteTodo(int id) {
> 		Iterator<Todo> iterator = todos.iterator();
> 		while (iterator.hasNext()) {
> 			Todo todo = iterator.next();
> 			if (todo.getId() == id) {
> 				iterator.remove();
> 			}
> 		}
> 	}
> 	
> }
> ```

------

> **/views/login.jsp**
>
> ```jsp
> <body>
> <form action="/login-form" method="post">
> 	${errorMessage}
> 	<br><br>
> 	Enter name <input type="text" name="user_name"/><br><br>
> 	Enter password <input type="password" name="user_password"/><br><br>
> 	<input type="submit"value="Login"/>
> </form>
> </body>
> ```

> **/views/welcome.jsp**
>
> ```jsp
> <body>
> Welcome: 
> ${username} ,  ${password} 
> Now you can manage your todos <a href="/list-todos">Todo</a>
> </body>
> ```

> /views/list-todos.jsp
>
> ```jsp
> <body>
> hi i amm ${username} <br>
> 	${todos}
> </body>
> ```

 

## Step 10: Two important thing: "redirect:/list-todos" and "model.clear();"

- Add Facility to add New Todo
- todo.jsp
- Importance of redirect:/list-todos
- Importance of model.clear();

> **TodoController.java**
>
> ```java
> @Controller
> @SessionAttributes("username")
> public class TodoController {
> 	
> 	@Autowired
> 	TodoService service;
> 	
> 	@RequestMapping(value="/list-todos", method=RequestMethod.GET )
> 	public String showTodoPage(ModelMap model){
> 		// model.addAttribute("todos", service.retrieveTodos("in28minutes"));
> 		model.addAttribute("todos", service.retrieveTodos());		
> 		return "list-todos";
> 	}
> 	
> 	@RequestMapping(value="/add-todo", method =RequestMethod.GET)
> 	public String addTodo() {
> 		return "add-todo";
> 	}
> 	
> 	@RequestMapping(value="/add-todo", method =RequestMethod.POST)
> 	public String addTodoNew(ModelMap model, @RequestParam String name, @RequestParam String desc) {
> 		service.addTodo(name, desc, new Date(), false); // for the moment date and boolan are hard coded
> 		return "redirect:list-todos";
> 	}
> }
> ```

> **Todo.java** ->  Same as before

> **TodoService.java** -> added method 
>
> ```java
> public List<Todo> retrieveTodos( ) {
> 		List<Todo> filteredTodos = new ArrayList<Todo>();
> 		for (Todo todo : todos) {
> 			filteredTodos.add(todo);
> 		}
> 		return filteredTodos;
> 	}
> ```

------

> **login.jsp**
>
> ```jsp
> <body>
> 	<form action="/login-form" method="post">
> 		${errorMessage} <br>
> 		<br> Enter name <input type="text" name="user_name" /><br>
> 		<br> Enter password <input type="password" name="user_password" /><br>
> 		<br> <input type="submit" value="Login" />
> 	</form>
> </body>
> ```

> **welcome.jsp**
>
> ```jsp
> <body>
> 	Welcome: ${username} , ${password} Now you can manage your todos
> 	<a href="/list-todos">Todo</a>
> 
> </body>
> ```

> **list-todo.jsp**
>
> ```jsp
> <body>
> hi i amm ${username} <br>
> 	${todos}
> 	
> 	<br><br>
> 	<a href="/add-todo">Add new</a>
> </body>
> ```

> **add-todo.jsp** it will redirect to list-todo.jsp page 
>
> ```jsp
> <body>
> 	<h1>add to do</h1>
> 	<form action="" method="post">
> 		<input type="text" name="name" />
> 		<input type="text" name="desc" />
> 		<input type="submit" value="submit" />
> 	</form>
> </body>
> ```

